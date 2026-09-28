<!DOCTYPE html>
<html>
<head>
    <title>Part 6 - Session Read</title>
</head>
<body>
    <h2>Part 6 - Session Read</h2>

    <?php
        // Resume the existing session
        session_start();

        // Check if session variables exist
        if (isset($_SESSION["username"])) {
            echo "<h3 style='color:green'>Session variables found!</h3>";
            echo "<p>Session ID: " . session_id() . "</p>";
            echo "<p>Username: " . $_SESSION["username"] . "</p>";
            echo "<p>Role: " . $_SESSION["role"] . "</p>";
            echo "<p>Login Time: " . $_SESSION["login_time"] . "</p>";
        } else {
            echo "<h3 style='color:red'>No session found!</h3>";
            echo "<p>Please go back and start a session first.</p>";
        }

        echo "<br>";
        echo "<a href='part6_session_destroy.php'>Destroy Session</a>";
    ?>

</body>
</html>
