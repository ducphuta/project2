<?php 
$account = $_POST['account'];
$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM comment WHERE accpost = '$account'";

$data = mysqli_query($connect, $query);

class Comment {

	function Comment($ID, $acc, $accpost, $content, $date, $time){
		$this->ID = $ID;
		$this->acc = $acc;
		$this->accpost = $accpost;
		$this->content = $content;
		$this->date = $date;
		$this->time = $time;
	}
}

$post = array();

while ($row = mysqli_fetch_row($data)) {
	array_push($post, new Post($row['0'], $row['1'], $row['2'], $row['3'], $row['4'], $row['5']));
}

echo json_encode($post);

 ?>