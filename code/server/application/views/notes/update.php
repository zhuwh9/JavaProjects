<?php
$request_data = json_decode($this->input->post("request_data"));

if ($row = validate_user()) {
	$this->db->where("user_id", $row->user_id);
	$this->db->where("note_id", (int)$request_data->id);
	$result = $this->db->get("notes");
	if ($row = $result->unbuffered_row()) {
		$data = [
				"title"       => $request_data->title,
				"text"       => $request_data->text,
				"modify" => date('Y-m-d H:i:s'),
		];
		$this->db->where("user_id", $row->user_id);
		$this->db->where("note_id", (int)$request_data->id);
		$this->db->update("notes", $data);

		echo json_encode([
			"status" => 0
		]);
	} else {
		echo json_encode([
			"status" => 12450
		]);
	}
} else {

	echo json_encode([
		"status" => 500
	]);
}

