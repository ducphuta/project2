<?php 
$ID = $_POST['ID'];
$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");
$query = "DELETE FROM save WHERE IDpost = '$ID'";
$query1 = "DELETE FROM comment WHERE IDpost = '$ID'";
$query2 = "DELETE FROM post WHERE ID = '$ID'";
if(mysqli_query($connect, $query)){
	if (mysqli_query($connect, $query1)) {
		if(mysqli_query($connect, $query2)){
	echo "success";
}

else {
	echo "error";
}

	}
	else{
		echo "error";
	}
}

else{
	echo "error";
}
 ?>
