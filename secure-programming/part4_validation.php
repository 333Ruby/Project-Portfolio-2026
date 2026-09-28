<!DOCTYPE html>
<html>
<head>
    <title>Part 4 - PHP Validation</title>
</head>
<body>
    <h2>Part 4 - PHP Form Validation</h2>

    <form action="part4_validation.php" method="POST">

        <label>Student ID (8 digits):</label><br>
        <input type="text" name="student_id"><br><br>

        <label>Email Address:</label><br>
        <input type="text" name="email"><br><br>

        <label>Full Name:</label><br>
        <input type="text" name="fullname"><br><br>

        <label>Message:</label><br>
        <textarea name="message"></textarea><br><br>

        <input type="submit" value="Submit">

    </form>

    <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {

            $errors = [];
            $safe_values = [];

            // -----------------------------------------------
            // 1. empty() - Check that no fields are blank
            // -----------------------------------------------
            if (empty($_POST["student_id"])) {
                $errors[] = "Student ID cannot be empty.";
            }
            if (empty($_POST["email"])) {
                $errors[] = "Email cannot be empty.";
            }
            if (empty($_POST["fullname"])) {
                $errors[] = "Full name cannot be empty.";
            }
            if (empty($_POST["message"])) {
                $errors[] = "Message cannot be empty.";
            }

            // -----------------------------------------------
            // 2. preg_match() - Regex to validate student ID
            // Must be exactly 8 digits, nothing else
            // -----------------------------------------------
            $student_id = $_POST["student_id"];
            if (!preg_match("/^\d{8}$/", $student_id)) {
                $errors[] = "Student ID must be exactly 8 digits.";
            } else {
                $safe_values["student_id"] = $student_id;
            }

            // -----------------------------------------------
            // 3. filter_input() - Validate email address
            // FILTER_VALIDATE_EMAIL checks for valid email format
            // -----------------------------------------------
            $email = filter_input(INPUT_POST, "email", FILTER_VALIDATE_EMAIL);
            if (!$email) {
                $errors[] = "Email address is not valid.";
            } else {
                $safe_values["email"] = $email;
            }

            // -----------------------------------------------
            // 4. htmlspecialchars() - Sanitise full name
            // Converts dangerous characters like < > & into
            // harmless HTML entities to prevent XSS
            // -----------------------------------------------
            $fullname = htmlspecialchars($_POST["fullname"], ENT_QUOTES, 'UTF-8');
            $safe_values["fullname"] = $fullname;

            // -----------------------------------------------
            // 5. strip_tags() - Remove any HTML/PHP tags
            // from the message field
            // -----------------------------------------------
            $message = strip_tags($_POST["message"]);
            $safe_values["message"] = $message;

            // -----------------------------------------------
            // Display errors or success
            // -----------------------------------------------
            if (!empty($errors)) {
                echo "<h3 style='color:red'>Errors found:</h3>";
                echo "<ul>";
                foreach ($errors as $error) {
                    echo "<li style='color:red'>" . $error . "</li>";
                }
                echo "</ul>";
            } else {
                echo "<h3 style='color:green'>All inputs are valid!</h3>";
                echo "<p>Student ID: " . $safe_values["student_id"] . "</p>";
                echo "<p>Email: " . $safe_values["email"] . "</p>";
                echo "<p>Full Name: " . $safe_values["fullname"] . "</p>";
                echo "<p>Message: " . $safe_values["message"] . "</p>";
            }
        }
    ?>

</body>
</html>
