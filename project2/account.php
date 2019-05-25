<?php 
$account = $_POST['account'];
$password = $_POST['password'];
$name = $_POST['name'];
$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "INSERT INTO user VALUES('$account', '$password', '$name')";
if(mysqli_query($connect, $query))
{
	echo "success";
}
else
{
	echo "error";
}

 ?>