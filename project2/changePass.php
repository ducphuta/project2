<?php 
$acc = $_POST['acc'];
$pass = $_POST['pass'];


$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "UPDATE user SET password = '$pass' WHERE account = '$acc'";

if (mysqli_query($connect, $query)) {
echo "success";
}
else{
	echo "error";
}

 ?>