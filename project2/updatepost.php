<?php 
$id = $_POST['id'];
$date = $_POST['date'];
$time = $_POST['time'];
$content = $_POST['content'];
$salary = $_POST['salary'];
$place = $_POST['place'];
$address = $_POST['address'];

// $id = "6";
// $date = "a";
// $time = "a";
// $content = "a";
// $salary = "a";
// $place = "a";
// $address = "a";

$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "UPDATE post SET salary = '$salary', content = '$content', date = '$date', time = '$time', address = '$address', place = '$place' WHERE ID = '$id'";

if (mysqli_query($connect, $query)) {
echo "success";
}
else{
	echo "error";
}

 ?>