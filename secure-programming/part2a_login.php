<!DOCTYPE html>
<html>
<head>
    <title>Part 2a - Login Page</title>
</head>
<body>
    <h2>Part 2a - Login Page</h2>

    <form action="part2a_login.php" method="POST">
        
        <label>Username:</label><br>
        <input type="text" name="username"><br><br>

        <label>Password:</label><br>
        <input type="password" name="password"><br><br>

        <input type="submit" value="Login">

    </form>

    <?php
        // if the form is submitted it will be proccesed 
        if ($_SERVER["REQUEST_METHOD"] == "POST") {

            // values sent from the form are retrieved here
            $username = $_POST["username"];
            $password = $_POST["password"];

            try {
                // Connect to the database
                $dsn = 'mysql:host=localhost;dbname=ca3db';
                $conn = new PDO($dsn, 'your_username', 'your_password');
                $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

                // Build the query using the user's input
                $query = "SELECT * FROM users WHERE username = '$username' AND password = '$password'";

                // the query is executed here
                $result = $conn->query($query);

                // Check if a matching user was found
                $row = $result->fetch();

                if ($row) {
                    echo "<h3 style='color:green'>Login Successful! Welcome " . $username . "</h3>";
                } else {
                    echo "<h3 style='color:red'>Login Failed! Invalid username or password.</h3>";
                }

            } catch(PDOException $e) {
                echo "ERROR: " . $e->getMessage();
            }

            // Close connection
            $conn = null;
        }
    ?>

</body>
</html>
