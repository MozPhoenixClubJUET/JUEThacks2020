<?php
	$conn = new mysqli('localhost', 'root', 'makshad2020', 'ignoramusindia');
	if(mysqli_connect_error()) {
		die('Connect Error('. mysqli_connect_errno().')'. mysqli_connect_error());
	} else {
	$query = "Create table if not exists users (userId Int(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, usertype Int(1), Mob_Number Int(10) NOT NULL, Email varchar(50) NOT NULL, Password Text(50) NOT NULL";
	mysqli_query($conn, $query);
	
	$query = "Create table if not exists Students (userId Int(6), name text, Mob_Number Int(10) NOT NULL, address text, city text, state text, dateOfBirth DATE, school text, description text, Email varchar(50) NOT NULL, Password Text(50) NOT NULL, FOREIGN KEY (userId) references users(userId)";
	mysqli_query($conn, $query);
	
	$query = "Create table if not exists Workers (userId Int(6), name text, Mob_Number Int(10) NOT NULL, address text, city text, state text, dateOfBirth DATE, Email varchar(50) NOT NULL, Password Text(50) NOT NULL, FOREIGN KEY (userId) references users(userId)";
	mysqli_query($conn, $query);
	
	$query = "Create table if not exists Customer (userId Int(6), name text, Mob_Number Int(10) NOT NULL, address text, city text, state text, dateOfBirth DATE, profession text, profession_desc text, expSalary int(1), salary Integer(5), Email varchar(50) NOT NULL, Password Text(50) NOT NULL, FOREIGN KEY (userId) references users(userId)";
	mysqli_query($conn, $query);
	
	$query = "Create table if not exists Products (userId Int(6), productName Text, productType text, productDesc text, productCharge Text, processTime text, FOREIGN KEY (userId) REFERENCES users(userId))";
	mysqli_query($conn, $query);
	}
	
?>