error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/RAG/KnowledgeBaseLoader.java:local4
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/RAG/KnowledgeBaseLoader.java
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol local4
empty definition using fallback
non-local guesses:

offset: 1366
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/RAG/KnowledgeBaseLoader.java
text:
```scala
package techskill.demo.RAG;


import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class KnowledgeBaseLoader implements ApplicationRunner{
    private final VectorStore vectorStore;
    private final JdbcTemplate jdbcTemplate;
    private final Resource[] knowledgeFiles;

    public KnowledgeBaseLoader(VectorStore vectorStore,
        JdbcTemplate jdbcTemplate,
        @Value("classpath:knowledge/*.txt") Resource[] knowledgeFiles) {
this.vectorStore = vectorStore;
this.jdbcTemplate = jdbcTemplate;
this.knowledgeFiles = knowledgeFiles;
}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM vector_store", Integer.class);
        if (count@@ != null && count > 0) {
            System.out.println("RAG: already loaded (" + count + " rows). Skipping.");
            return;
        }

        List<Document> docs = new ArrayList<>();
        for (Resource file : knowledgeFiles) {
            String content = file.getContentAsString(StandardCharsets.UTF_8);
            docs.add(new Document(content, Map.of("source", file.getFilename())));
        }

        List<Document> chunks = new TokenTextSplitter().apply(docs);
        vectorStore.add(chunks);
        System.out.println("RAG: loaded " + chunks.size() + " chunks from "
            + knowledgeFiles.length + " files.");
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 