<!DOCTYPE html>
<html>
<head>
    <title>Part 1c - XSS Vulnerable</title>
</head>
<body>
    <h2>Part 1c - XSS Vulnerable Form</h2>

    <form action="part1c_xss_vulnerable.php" method="POST">
        
        <label>Enter your name:</label><br>
        <input type="text" name="username"><br><br>

        <input type="submit" value="Submit">

    </form>

    <?php
        // Vulnerable - directly echoing user input with no sanitisation
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $username = $_POST["username"];
            echo "<p>Hello, " . $username . "</p>";
        }
    ?>

</body>
</html>
