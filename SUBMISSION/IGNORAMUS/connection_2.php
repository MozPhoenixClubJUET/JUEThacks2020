<?php

    $conn = new mysqli('localhost', 'root', 'makshad2020', 'ignoramusindia');
    if(mysqli_connect_error()) {
        die('Connect Error('. mysqli_connect_errno().')'. mysqli_connect_error());
    } else {

    $name = $_POST["username"];
    $mobile = $_POST['Mobile'];
    $address = $_POST['address'];
    $city = $_POST['city'];
    $state = $_POST['state'];
    $DOB = $_POST['dob'];
    $profession = $_POST['profession'];
    $profession_desc = $_POST["profession_desc"];
    $salary = $_POST['pos'];
    $charge = $_POST['charge'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $usertype = '1';

    $select = "Select Email, Mob_Number from users where Email = ? or Mob_No = ? Limit 1";
    $insert = "Insert into users (usertype, Mob_Number, Email, Password) values ($usertype, $mobile, $email, $password)";

    if(mysqli_query($conn, $select)) {
        echo "already exists";
    } else {
        mysqli_query($conn, $insert);
        $select = "Select Email, Mob_Number from Custommers where Email = ? or Mob_No = ? Limit 1";
        $insert = "Insert into Workers  (name, Mob_Number, address, city, state, dateofBirth, profession, profession_desc, expSalary, salary, Email, Password) values ($name, $mobile, $address, $city, $state, $DOB, $profession, $profession_desc, $salary, $charge, $email, $password)";
        mysqli_query($conn, $insert);

        echo "<script type=\"text/javascript\">".
        "alert('success');".
        "</script>";
    }

    
    }
?>