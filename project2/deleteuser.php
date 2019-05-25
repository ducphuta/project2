<?php 

$account = $_POST['account'];
$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");
$query = "DELETE FROM save WHERE accPost = '$account'";
$query1 = "DELETE FROM comment WHERE acc = '$account'";
$query2 = "DELETE FROM post WHERE acc = '$account'";
$query3 = "DELETE FROM user WHERE account = '$account'";
if(mysqli_query($connect, $query)){
	if(mysqli_query($connect, $query1)){
		if (mysqli_query($connect, $query2)) {
			if (mysqli_query($connect, $query3)) {
				echo "success";
			}
			else echo "error";
		}
		else echo "error";
	}
	else echo "error";
	}
else{
	echo "error";
}

 ?>