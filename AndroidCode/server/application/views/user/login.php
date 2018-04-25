<?php
$request_data = json_decode($this->input->post("request_data"));
$username = $request_data->username;
$password = sha1($request_data->password);
$this->db->where("username", $username);
$this->db->where("password", $password);
$result = $this->db->get("users");
if ($row = $result->row()) {
	$data = ["accesstoken" => $hash = sha1(microtime())];
	$this->db->where("username", $username);
	$this->db->where("password", $password);
	$this->db->update("users", $data);
	echo json_encode([
		"status" => 0,
		"user_id" => $row->user_id,
		"email" => $row->email,
		"access_token" => $data["accesstoken"]
	]);
} else {
	echo json_encode([
		"status" => 2404
	]);
}