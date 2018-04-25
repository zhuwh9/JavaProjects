<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Notes extends CI_Controller {

	public function update()
	{
		header("Content-Type: application/json");
		$this->load->view('notes/update');
	}
	public function list()
	{
		header("Content-Type: application/json");
		$this->load->view('notes/list');
	}
	public function new()
	{
		header("Content-Type: application/json");
		$this->load->view('notes/new');
	}
	public function delete()
	{
		header("Content-Type: application/json");
		$this->load->view('notes/delete');
	}
}
