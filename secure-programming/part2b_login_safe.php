<!DOCTYPE html>
<html>
<head>
    <title>Part 2b - Safe Login Page</title>
</head>
<body>
    <h2>Part 2b - SQL Injection Safe Login Page</h2>

    <form action="part2b_login_safe.php" method="POST">
        
        <label>Username:</label><br>
        <input type="text" name="username"><br><br>

        <label>Password:</label><br>
        <input type="password" name="password"><br><br>

        <input type="submit" value="Login">

    </form>

    <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {

            $username = $_POST["username"];
            $password = $_POST["password"];

            try {
                $dsn = 'mysql:host=localhost;dbname=ca3db';
                $conn = new PDO($dsn, 'ca3user', 'ca3password');
                $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                $query = "SELECT * FROM users WHERE username = ? AND password = ?";
                $stmt = $conn->prepare($query);
                $stmt->execute([$username, $password]);

                $row = $stmt->fetch();

                if ($row) {
                    echo "<h3 style='color:green'>Login Successful! Welcome " . htmlspecialchars($username) . "</h3>";
                } else {
                    echo "<h3 style='color:red'>Login Failed! Invalid username or password.</h3>";
                }

            } catch(PDOException $e) {
                echo "ERROR: " . $e->getMessage();
            }

            $conn = null;
        }
    ?>

</body>
</html>
