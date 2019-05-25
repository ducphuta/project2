<?php 
$ID = $_POST['ID'];
$acc = $_POST['acc'];
$accpost = $_POST['accpost'];
$content = $_POST['content'];
$date = $_POST['date'];
$time = $_POST['time'];
$name = $_POST['name'];
$IDpost = $_POST['IDpost'];
$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "INSERT INTO comment(acc, accpost, content, date, time, name, IDpost) VALUES('$acc', '$accpost', '$content', '$date', '$time', '$name', '$IDpost')";
if(mysqli_query($connect, $query))
{
	echo "success";
}
else
{
	echo "error";
}

 ?>