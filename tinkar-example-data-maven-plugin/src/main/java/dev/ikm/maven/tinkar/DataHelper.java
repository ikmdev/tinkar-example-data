package dev.ikm.maven.tinkar;

import dev.ikm.tinkar.entity.graph.DiTreeEntity;
import dev.ikm.tinkar.entity.graph.EntityVertex;
import dev.ikm.tinkar.terms.TinkarTerm;

public final class DataHelper {

    private DataHelper() {
    }

    public static DiTreeEntity createSampleDiTreeEntity() {
        EntityVertex definitionRootVertex = EntityVertex.make(TinkarTerm.DEFINITION_ROOT.nid());
        EntityVertex andVertex = EntityVertex.make(TinkarTerm.AND.nid());
        EntityVertex necessarySetVertex = EntityVertex.make(TinkarTerm.NECESSARY_SET.nid());

        EntityVertex conceptReferenceVertex = EntityVertex.make(TinkarTerm.CONCEPT_REFERENCE.nid());
        conceptReferenceVertex.putUncommittedProperty(TinkarTerm.CONCEPT_REFERENCE.nid(), TinkarTerm.ANONYMOUS_CONCEPT);
        conceptReferenceVertex.commitProperties();

        DiTreeEntity.Builder expectedMergedDteBuilder = DiTreeEntity.builder();
        expectedMergedDteBuilder.setRoot(definitionRootVertex);
        expectedMergedDteBuilder.addVertex(andVertex);
        expectedMergedDteBuilder.addVertex(necessarySetVertex);
        expectedMergedDteBuilder.addEdge(necessarySetVertex.vertexIndex(), definitionRootVertex.vertexIndex());
        expectedMergedDteBuilder.addEdge(andVertex.vertexIndex(), necessarySetVertex.vertexIndex());
        expectedMergedDteBuilder.addVertex(conceptReferenceVertex);
        expectedMergedDteBuilder.addEdge(conceptReferenceVertex.vertexIndex(), andVertex.vertexIndex());
        return expectedMergedDteBuilder.build();
    }

}
