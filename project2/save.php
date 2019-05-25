<?php 
$account = $_POST['account'];
$IDpost = $_POST['IDpost'];
$accPost = $_POST['accPost'];

$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "INSERT INTO save VALUES('$account', '$IDpost', '$accPost')";
if(mysqli_query($connect, $query))
{
	echo "success";
}
else
{
	echo "error";
}

 ?>