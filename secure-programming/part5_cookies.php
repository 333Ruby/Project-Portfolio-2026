<!DOCTYPE html>
<html>
<head>
    <title>Part 5 - Cookies</title>
</head>
<body>
    <h2>Part 5 - Cookies</h2>

    <?php
        // Cookie name and value
        $cookie_name = "user_visit";
        $cookie_value = "Welcome back!";

        // Cookie expiry time - 1 hour from now
        // time() returns current time in seconds
        // 3600 = 60 minutes x 60 seconds = 1 hour
        $expiry = time() + 3600;

        // Check if the cookie already exists
        if (!isset($_COOKIE[$cookie_name])) {

            // if the cookie doesn't exist create it
            // Parameters: name, value, expiry, path, domain, secure, httponly
            setcookie(
                $cookie_name,   // Name of the cookie
                $cookie_value,  // Value stored in the cookie
                $expiry,        // Expiry time
                "/",            // Path - available across whole website
                "",             // Domain - empty means current domain
                false,          // Secure - should be true on HTTPS
                true            // HttpOnly - prevents JavaScript access
            );

            echo "<h3 style='color:blue'>Cookie did not exist — it has been created!</h3>";
            echo "<p>Cookie Name: " . $cookie_name . "</p>";
            echo "<p>Cookie Value: " . $cookie_value . "</p>";
            echo "<p>Expires in: 1 hour</p>";
            echo "<p>Please refresh the page to read the cookie.</p>";

        } else {

            // Cookie already exists - read its value
            $stored_value = $_COOKIE[$cookie_name];

            echo "<h3 style='color:green'>Cookie already exists — reading its value!</h3>";
            echo "<p>Cookie Name: " . $cookie_name . "</p>";
            echo "<p>Cookie Value: " . $stored_value . "</p>";

        }
    ?>

    <br>
    <h3>All Current Cookies:</h3>
    <?php
        // Display all cookies currently set
        if (!empty($_COOKIE)) {
            foreach ($_COOKIE as $name => $value) {
                echo "<p>" . htmlspecialchars($name) . " = " . htmlspecialchars($value) . "</p>";
            }
        } else {
            echo "<p>No cookies found.</p>";
        }
    ?>

    <br>
    <!-- Button to delete the cookie -->
    <form action="part5_cookies.php" method="POST">
        <input type="submit" name="delete_cookie" value="Delete Cookie">
    </form>

    <?php
        // Delete the cookie if button was clicked
        // Setting expiry time in the past forces the browser to delete it
        if (isset($_POST["delete_cookie"])) {
            setcookie($cookie_name, "", time() - 3600, "/");
            echo "<h3 style='color:red'>Cookie has been deleted! Refresh to confirm.</h3>";
        }
    ?>

</body>
</html>
