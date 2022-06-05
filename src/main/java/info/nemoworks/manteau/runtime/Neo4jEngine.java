//package info.nemoworks.manteau.runtime;
//
//
//import org.neo4j.configuration.connectors.BoltConnector;
//import org.neo4j.configuration.helpers.SocketAddress;
//import org.neo4j.dbms.api.DatabaseManagementService;
//import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
//import org.neo4j.graphdb.*;
//import org.neo4j.graphdb.config.Setting;
//import org.neo4j.io.fs.FileUtils;
//
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
//
//import static org.neo4j.configuration.GraphDatabaseSettings.DEFAULT_DATABASE_NAME;
//
//public class Neo4jEngine implements RuntimeService {
//
//    private static final Path DB_PATH = Path.of("neo4jdb");
//
//    private GraphDatabaseService graphDb;
//
//    public Neo4jEngine() throws IOException {
//        FileUtils.deleteDirectory(DB_PATH);
//
//        DatabaseManagementService managementService = new DatabaseManagementServiceBuilder(DB_PATH)
//                .setConfig(BoltConnector.enabled, true)
//                .setConfig(BoltConnector.listen_address, new SocketAddress("localhost", 7687)).
//                build();
//        this.graphDb = managementService.database(DEFAULT_DATABASE_NAME);
//        registerShutdownHook(managementService);
//
//
//    }
//
//    @Override
//    public void execute(RuntimeModel runtimeModel) {
//
//        try (Transaction tx = graphDb.beginTx()) {
//
//
//            Node firstNode = tx.createNode();
//            firstNode.setProperty("message", "Hello, ");
//            Node secondNode = tx.createNode();
//            secondNode.setProperty("message", "World!");
//
//            Relationship relationship = firstNode.createRelationshipTo(secondNode, new RelationshipType() {
//                @Override
//                public String name() {
//                    return "KNOWS";
//                }
//            });
//            relationship.setProperty("message", "brave Neo4j ");
//            tx.commit();
//        }
//    }
//
//    private static void registerShutdownHook(final DatabaseManagementService managementService) {
//        // Registers a shutdown hook for the Neo4j instance so that it
//        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
//        // running application).
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                managementService.shutdown();
//            }
//        });
//    }
//
//    public static void main(String[] args) throws IOException {
//        Neo4jEngine engine = new Neo4jEngine();
////        engine.execute(null);
//        System.in.read();
//    }
//}
