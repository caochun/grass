//package info.nemoworks.manteau.storage;
//
//import com.arcadedb.database.Database;
//import com.arcadedb.database.RID;
//import com.arcadedb.graph.MutableVertex;
//import com.arcadedb.graph.Vertex;
//import com.arcadedb.index.IndexCursor;
//import com.arcadedb.schema.EdgeType;
//import com.arcadedb.schema.Schema;
//import com.arcadedb.schema.Type;
//import com.arcadedb.schema.VertexType;
//import info.nemoworks.manteau.core.GObject;
//import info.nemoworks.manteau.core.GReference;
//import info.nemoworks.manteau.core.ModelStorage;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ArcadeStorage implements ModelStorage {
//
//    private Database db;
//
//    @Autowired
//    public void setDatabase(Database database) {
//        this.db = database;
//    }
//
//    @Override
//    public boolean save(GObject gObject) {
//
//        try {
//            db.begin();
//
//            try {
//                db.getSchema().getType(gObject.getClassName());
//            } catch (Exception e) {
//                VertexType vertexType = db.getSchema().getOrCreateVertexType(gObject.getClassName());
//                vertexType.createProperty("_id", Type.STRING);
//            }
//
//            if (!db.getSchema().existsIndex(gObject.getClassName() + "[_id]")) {
//                db.getSchema().createTypeIndex(Schema.INDEX_TYPE.LSM_TREE, false, gObject.getClassName(), "_id");
//            }
//
//            MutableVertex vertex = db.newVertex(gObject.getClassName()).save();
//            gObject.put("_RID", vertex.getIdentity());
//
//            vertex.set(gObject).save();
//
//            db.commit();
//            return true;
//        } catch (Exception e) {
//            db.rollback();
//        }
//        return false;
//    }
//
//
//    @Override
//    public GObject get(String className, String id) {
//        IndexCursor indexCursor = db.lookupByKey(className, new String[]{"_id"}, new String[]{id});
//        if (indexCursor.hasNext()) {
//            return GObject.fromMap(className, indexCursor.next().asVertex().toMap());
//        }
//
//        return null;
//    }
//
//    private Vertex loadVertex(@NotNull GObject gObject) {
//        if (gObject.get("_RID") != null) {
//            return db.lookupByRID((RID) (gObject.get("_RID")), true).asVertex();
//        } else {
//            return db.lookupByKey(gObject.getClassName(), "_id", gObject.getId()).getRecord().asVertex(true);
//        }
//    }
//
//    @Override
//    public boolean addRelation(GReference reference) {
//
//        Vertex vFrom = loadVertex(reference.getFrom());
//        Vertex vTo = loadVertex(reference.getTo());
//
//        if ((vFrom == null) || (vTo == null)) {
//            return false;
//        }
//
//        try {
//            db.begin();
//
//            try {
//                db.getSchema().getType(reference.getRefName());
//            } catch (Exception e) {
//                EdgeType edgeType = db.getSchema().getOrCreateEdgeType(reference.getRefName());
//            }
//
//            vFrom.newEdge(reference.getRefName(), vTo, false);
//
//            db.commit();
//            return true;
//        } catch (Exception e) {
//            db.rollback();
//        }
//        return false;
//    }
//}
