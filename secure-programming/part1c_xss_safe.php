<!DOCTYPE html>
<html>
<head>
    <title>Part 1c - XSS Safe</title>
</head>
<body>
    <h2>Part 1c - XSS Safe Form</h2>

    <form action="part1c_xss_safe.php" method="POST">
        
        <label>Enter your name:</label><br>
        <input type="text" name="username"><br><br>

        <input type="submit" value="Submit">

    </form>

    <?php
        // Safe - user input is sanitised using htmlspecialchars()
        // This converts dangerous characters like < > & into harmless HTML entities
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $username = htmlspecialchars($_POST["username"], ENT_QUOTES, 'UTF-8');
            echo "<p>Hello, " . $username . "</p>";
        }
    ?>

</body>
</html>
