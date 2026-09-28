<!DOCTYPE html>
<html>
<head>
    <title>Part 3 - Basic Form Protection</title>
</head>
<body>
    <h2>Part 3 - Basic Form Protection</h2>

    <form action="part3_form_protection.php" method="POST">

        <!-- Demonstrating maxlength: restricts input to 8 chars -->
        <label>Student ID (max 8 characters):</label><br>
        <input type="text" name="student_id" maxlength="8"><br><br>

        <!-- Demonstrating readonly: field = uneditable in browser -->
        <label>Country (readonly):</label><br>
        <input type="text" name="country" value="Ireland" readonly="true"><br><br>

        <!-- Demonstrating hidden field: not visible to user -->
        <input type="hidden" name="role" value="student">

        <input type="submit" value="Submit">

    </form>

    <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            // Display whatever values were actually received
            $student_id = $_POST["student_id"];
            $country = $_POST["country"];
            $role = $_POST["role"];

            echo "<h3>Values Received:</h3>";
            echo "<p>Student ID: " . $student_id . "</p>";
            echo "<p>Country: " . $country . "</p>";
            echo "<p>Role: " . $role . "</p>";
        }
    ?>

</body>
</html>
