<?php 
$account = $_POST['account'];
$IDpost = $_POST['IDpost'];
$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "DELETE FROM save WHERE IDpost = '$IDpost' AND account = '$account'";

if(mysqli_query($connect, $query))
{
	echo "success";
}
else
{
	echo "error";
}

 ?>