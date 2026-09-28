<?php
    // Check the form was actually submitted via GET
    if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["username"])) {

        // the values sent from the form get retrieved here
        $username = $_GET["username"];
        $message = $_GET["message"];

        // Display back to user
        echo "<h2>Form Submitted Successfully</h2>";
        echo "<p>Name: " . $username . "</p>";
        echo "<p>Message: " . $message . "</p>";

    } else {
        echo "<p>No form data received.</p>";
    }
?>
