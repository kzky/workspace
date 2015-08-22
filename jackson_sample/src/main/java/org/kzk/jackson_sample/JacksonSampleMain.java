package org.kzk.jackson_sample;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


// json形式ファイルのパースのサンプル
public class JacksonSampleMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();

		try {
			JsonNode rootNode = mapper.readValue(new File("/home/kzk/workspace/jackson_sample/src/main/resources/jackson_sample.json"),
					JsonNode.class);

			System.out.println(rootNode.asText());
			
			JsonNode current = null;
			for (int i = 0; (current = rootNode.get(i)) != null; i++) {
				JsonNode nameNode = current.get("name");
				System.out.println("name: ");
				
				Iterator<String> fieldNames = nameNode.getFieldNames();
				while (fieldNames.hasNext()) {
					String fieldName = fieldNames.next();
					System.out.println("\t" + fieldName + ": " + nameNode.get(fieldName));
				}
				JsonNode mailNode = current.get("mail");
				System.out.println("mail: " + mailNode.getTextValue());
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
