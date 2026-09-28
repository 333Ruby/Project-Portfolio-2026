<?php
    // Check to see if the form was submitted
    if ($_SERVER["REQUEST_METHOD"] == "POST") {

        // the values sent from the form get retrieved here
        $username = $_POST["username"];
        $message = $_POST["message"];

        // Display back to the user
        echo "<h2>Form Submitted Successfully</h2>";
        echo "<p>Name: " . $username . "</p>";
        echo "<p>Message: " . $message . "</p>";

    } else {
        // If someone visits this page directly without submitting
        echo "<p>No form data received.</p>";
    }
?>
