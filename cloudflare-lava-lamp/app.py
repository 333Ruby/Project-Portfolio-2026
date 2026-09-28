import cv2
import hashlib
import numpy as np
from cryptography.fernet import Fernet
from flask import Flask, jsonify, request, send_file
from flask_cors import CORS
import base64

app = Flask(__name__)
CORS(app)

VIDEO_SOURCE = "lava.mp4"

# ── Entropy ───────────────────────────────────────────────────────────────────

def capture_entropy():
    cap = cv2.VideoCapture(VIDEO_SOURCE)
    if not cap.isOpened():
        raise RuntimeError("Could not open video source")

    ret1, frame1 = cap.read()
    ret2, frame2 = cap.read()
    cap.release()

    if not ret1 or not ret2:
        raise RuntimeError("Could not read frames")

    diff = cv2.absdiff(frame1, frame2)
    raw_bytes = diff.tobytes()
    entropy = hashlib.sha256(raw_bytes).hexdigest()
    score = round(min((float(np.mean(diff)) / 255) * 100, 100), 2)

    return entropy, score

# ── Key generation ────────────────────────────────────────────────────────────

def generate_key(entropy):
    key_bytes = entropy[:32].encode('utf-8')
    return base64.urlsafe_b64encode(key_bytes)

# ── Encryption ────────────────────────────────────────────────────────────────

def encrypt(message, key):
    cipher = Fernet(key)
    return cipher.encrypt(message.encode('utf-8')).decode('utf-8')

def decrypt(encrypted_message, key):
    cipher = Fernet(key.encode('utf-8'))
    return cipher.decrypt(encrypted_message.encode('utf-8')).decode('utf-8')

# ── Routes ────────────────────────────────────────────────────────────────────

@app.route('/')
def index():
    return send_file('index.html')

@app.route('/api/entropy', methods=['GET'])
def get_entropy():
    try:
        entropy, score = capture_entropy()
        return jsonify({
            "entropy": entropy,
            "score": score
        })
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route('/api/encrypt', methods=['POST'])
def encrypt_route():
    try:
        data = request.get_json()
        message = data.get('message', '')
        if not message:
            return jsonify({"error": "No message provided"}), 400

        entropy, score = capture_entropy()
        key = generate_key(entropy)
        encrypted = encrypt(message, key)

        return jsonify({
            "key": key.decode('utf-8'),
            "encrypted": encrypted,
            "entropy_score": score
        })
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route('/api/decrypt', methods=['POST'])
def decrypt_route():
    try:
        data = request.get_json()
        encrypted = data.get('encrypted', '')
        key = data.get('key', '')
        if not encrypted or not key:
            return jsonify({"error": "Missing fields"}), 400

        result = decrypt(encrypted, key)
        return jsonify({"decrypted": result})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, port=5000)