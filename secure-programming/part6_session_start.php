<!DOCTYPE html>
<html>
<head>
    <title>Part 6 - Session Start</title>
</head>
<body>
    <h2>Part 6 - Session Start</h2>

    <?php
        // Start the session
        session_start();

        // Set session variables
        $_SESSION["username"] = "admin";
        $_SESSION["role"] = "administrator";
        $_SESSION["login_time"] = date("Y-m-d H:i:s");

        // Display the session ID
        echo "<h3>Session Created Successfully!</h3>";
        echo "<p>Session ID: " . session_id() . "</p>";
        echo "<p>Username: " . $_SESSION["username"] . "</p>";
        echo "<p>Role: " . $_SESSION["role"] . "</p>";
        echo "<p>Login Time: " . $_SESSION["login_time"] . "</p>";
        echo "<br>";
        echo "<a href='part6_session_read.php'>Go to Session Read Page</a>";
    ?>

</body>
</html>
