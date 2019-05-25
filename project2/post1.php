<?php 

$acc = $_POST['acc'];
$salary = $_POST['salary'];
$address = $_POST['address'];
$place = $_POST['place'];
$content = $_POST['content'];
$date = $_POST['date'];
$time = $_POST['time'];
$name = $_POST['name'];
$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "INSERT INTO post(acc, salary, address, place, content, date, time, name) VALUES('$acc', '$salary','$address', '$place', '$content', '$date', '$time', '$name')";
if(mysqli_query($connect, $query))
{
	echo "success";
}
else
{
	echo "error";
}

 ?>
