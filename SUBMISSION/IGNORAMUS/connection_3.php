<?php

    $conn = new mysqli('localhost', 'root', 'makshad2020', 'ignoramusindia');
    if(mysqli_connect_error()) {
        die('Connect Error('. mysqli_connect_errno().')'. mysqli_connect_error());
    } else {

    $name = $_POST["exampleInputName3"];
    $mobile = $_POST['exampleInputMobile3'];
    $address = $_POST['exampleInputAddress3'];
    $city = $_POST['exampleInputCity3'];
    $state = $_POST['exampleInputState3'];
    $DOB = $_POST['exampleInputDOB3'];
    $email = $_POST['exampleInputEmail3'];
    $password = $_POST['exampleInputPassword3'];
    $usertype = '2';

    $select = "Select Email, Mob_Number from users where Email = ? or Mob_No = ? Limit 1";
    $insert = "Insert into users (usertype, Mob_Number, Email, Password) values ($usertype, $mobile, $email, $password)";

    if(mysqli_query($conn, $select)) {
        echo "already exists";
    } else {
        mysqli_query($conn, $insert);
        $select = "Select Email, Mob_Number from Custommers where Email = ? or Mob_No = ? Limit 1";
        $insert = "Insert into Customers  (name, Mob_Number, address, city, state, dateofBirth, Email, Password) values ($name, $mobile, $address, $city, $state, $DOB, $email, $password)";
        mysqli_query($conn, $insert);

        echo "<script type=\"text/javascript\">".
        "alert('success');".
        "</script>";
    }

    
    }
?>