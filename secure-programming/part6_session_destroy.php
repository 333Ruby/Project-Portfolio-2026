<!DOCTYPE html>
<html>
<head>
    <title>Part 6 - Session Destroy</title>
</head>
<body>
    <h2>Part 6 - Session Destroy</h2>

    <?php
        // Resume the existing session
        session_start();

        // Store session ID before destroying
        $old_session_id = session_id();

        // Clear all session variables
        $_SESSION = [];

        // Destroy the session completely
        session_destroy();

        echo "<h3 style='color:red'>Session Destroyed!</h3>";
        echo "<p>The session with ID: " . $old_session_id . " has been destroyed.</p>";
        echo "<p>All session variables have been cleared.</p>";
        echo "<br>";
        echo "<a href='part6_session_read.php'>Try to read session</a>";
    ?>

</body>
</html>
