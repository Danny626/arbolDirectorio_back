package com.albo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albo.exception.InternalException;
import com.albo.model.FileAux;
import com.albo.model.Node;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/directorio")
public class DirectorioController {

	private String[] nombreArchivo = { "C:\\jsonCompleto.json", "C:\\jsonFolders.json" };

// esta es la funcion oficial
	@GetMapping(value = "/listarFullDirectorios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringBuilder> listarFull() {
		boolean flagRelacion = true;
		String folder = "\\\\192.168.200.96\\SGC copia";
//		String folder = "d:\\herramientas";
		StringBuilder sb = new StringBuilder();

		try {
			String jsonInString = new ObjectMapper().writerWithDefaultPrettyPrinter()
					.writeValueAsString(getNodeFull(new File(folder)));
			sb.append("{\"data\":[");
			sb.append(jsonInString);
			sb.append("]}");
			guardarJson(sb.toString(), nombreArchivo[0]);
		} catch (IOException e) {
			e.printStackTrace();
			flagRelacion = false;
		}

		if (flagRelacion) {
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.OK);
		} else {
			throw new InternalException("Error");
		}

	}

	// funcion crea archivo para guardar json
	public void guardarJson(String jsonText, String nombreArchivo) throws IOException {

		Path path = Paths.get(nombreArchivo);
		byte[] strToBytes = jsonText.getBytes();

		Files.write(path, strToBytes);

	}
	// fin funcion guardar json

	@PostMapping(value = "/listarDirectorios", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringBuilder> generaJson(@RequestBody FileAux fileAux) {
		boolean flagRelacion = true;
		String folder = fileAux.getData();
		StringBuilder sb = new StringBuilder();
		String jsonInString = "";

		try {

//			String jsonInString = new ObjectMapper().writerWithDefaultPrettyPrinter()
//					.writeValueAsString(getNode(new File(folder)));

			File node = new File(folder);
			File nodeMC = new File("\\\\192.168.200.96\\\\SGC_2\\\\MC");
			File nodeSeguros = new File("\\\\192.168.200.96\\\\SGC_2\\\\SEGUROS");
			if (fileAux.getNivel() == 0) {
				Node nodo = new Node(node.getName(), node.getAbsolutePath(), null, "fa fa-folder-open", "fa fa-folder",
						"directory", getDirList2(node));

//				Node nodoMC = new Node(node.getName(), node.getAbsolutePath(), null, "fa fa-folder-open", "fa fa-folder",
//						"directory", getDirList2(nodeMC));
//				
//				Node nodoSeguros = new Node(node.getName(), node.getAbsolutePath(), null, "fa fa-folder-open", "fa fa-folder",
//						"directory", getDirList2(nodeSeguros));

				// prueba

//				List<Node> listaMC = new ArrayList<Node>();
//				List<Node> listaSeguros = new ArrayList<Node>();
//				
//				listaMC = getDirList2(nodeMC);
//				listaSeguros = getDirList2(nodeSeguros);

				// fin prueba

				jsonInString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(nodo);

				System.out.println(jsonInString);

				sb.append("{\"data\":[");
				sb.append(jsonInString);
				sb.append("]}");
			} else {
				jsonInString = new ObjectMapper().writerWithDefaultPrettyPrinter()
						.writeValueAsString(getDirList2(node));
				sb.append(jsonInString);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			flagRelacion = false;
		}

		if (flagRelacion) {
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.OK);
		} else {
			throw new InternalException("Error");
		}
	}

	@PostMapping(value = "/listarArchivos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringBuilder> filesJson(@RequestBody FileAux fileAux) {
		boolean flagRelacion = true;
		String folder = fileAux.getData();
		StringBuilder sb = new StringBuilder();
		String jsonInString = "";

		try {
			File node = new File(folder);
			sb.append("{\"data\":");
			jsonInString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(getFileList(node));
			sb.append(jsonInString);
			sb.append("}");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			flagRelacion = false;
		}

		if (flagRelacion) {
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.OK);
		} else {
			throw new InternalException("Error");
		}
	}

	// inicio métodos full carga
	public static Node getNodeFull(File node) {
		if (node.isDirectory()) {
			return new Node(node.getName(), node.getAbsolutePath(), null, "fa fa-folder-open", "fa fa-folder",
					"directory", getDirListFull(node));
//			return new Node(node.getName(), node.getAbsolutePath(), null, "fa fa-folder-open", "fa fa-folder",
//					"directory", null);
		} else {
			return new Node(node.getName(), node.getAbsolutePath(), "fa fa-file-image-o", null, null, "file", null);
		}
	}

	public static List<Node> getDirListFull(File node) {
		List<Node> nodeList = new ArrayList<>();
		for (File n : node.listFiles()) {
//			if (n.isDirectory()) {
			nodeList.add(getNodeFull(n));
//			}
		}
		return nodeList;
	}
	// fin métodos full carga

	public Node getNode(File node) {
		if (node.isDirectory()) {
			Node aux = new Node();
			aux.setLabel("Cargando..");
			aux.setData("dummy");
			aux.setIcon(null);
			aux.setExpandedIcon("fa fa-folder-open");
			aux.setCollapsedIcon("fa fa-folder");
			aux.setType("directory");
			aux.setChildren(null);
			List<Node> nodeList = new ArrayList<>();
			nodeList.add(aux);
			return new Node(node.getName(), node.getAbsolutePath(), null, "fa fa-folder-open", "fa fa-folder",
					"directory", nodeList);
		} else {
			return new Node(node.getName(), node.getAbsolutePath(), "fa fa-file-image-o", null, null, "file", null);
		}
	}

	public List<Node> getDirList2(File node) {
		List<Node> nodeList = new ArrayList<>();
		for (File n : node.listFiles()) {
			if (n.isDirectory()) {
				nodeList.add(getNode(n));
			}
		}
		return nodeList;
	}

	// para archivos
	public List<Node> getFileList(File node) {
		List<Node> nodeList = new ArrayList<>();
		for (File n : node.listFiles()) {
			if (!n.isDirectory()) {
				nodeList.add(getNode(n));
			}
		}
		return nodeList;
	}

	// ver archivo
	@PostMapping(value = "/verArchivo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> verArchivo(@RequestBody String urlArchivo) {

		byte[] fileBytes = null;
		File archivo = new File(urlArchivo);

		try {
			System.out.println(archivo.toPath());
			fileBytes = Files.readAllBytes(archivo.toPath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<byte[]>(fileBytes, HttpStatus.OK);
	}

	// manda el archivo json ya generado
	@GetMapping(value = "/obtenerJson", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> obtenerJson() {

		byte[] fileBytes = null;
		File archivo = new File(this.nombreArchivo[0]);

		try {
//			System.out.println(archivo.toPath());
			fileBytes = Files.readAllBytes(archivo.toPath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<byte[]>(fileBytes, HttpStatus.OK);
	}

}
