package org.semanticweb.owlapi.api.test.baseclasses;

import static org.semanticweb.owlapi.util.OWLAPIPreconditions.checkNotNull;

import java.util.Collection;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.semanticweb.owlapi.model.*;

/**
 * Created by ses on 10/1/14.
 */
class LiteralFoldingHashCoder implements OWLObjectVisitor, SWRLObjectVisitor {

    protected int hashCode;
    protected static final int MULT = 37;

    public static int hashCode(@Nullable OWLObject object) {
        checkNotNull(object, "object cannot be null");
        assert object != null;
        LiteralFoldingHashCoder hashCode = new LiteralFoldingHashCoder();
        object.accept(hashCode);
        return hashCode.hashCode;
    }

    /**
     * hash all literals with the same lexical form to the same value
     *
     * @param node
     *        node to add to hashcode
     */
    @Override
    public void visit(OWLLiteral node) {
        addValueToHash(node.getLiteral().hashCode());
    }

    @Override
    public void visit(OWLSubClassOfAxiom axiom) {
        addValueToHash(139);
        axiom.getSubClass().accept(this);
        axiom.getSuperClass().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLOntology ontology) {
        addValueToHash(ontology.getOntologyID().hashCode());
    }

    @Override
    public void visit(OWLAsymmetricObjectPropertyAxiom axiom) {
        addValueToHash(3);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLClassAssertionAxiom axiom) {
        addValueToHash(7);
        axiom.getIndividual().accept(this);
        axiom.getClassExpression().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDataPropertyAssertionAxiom axiom) {
        addValueToHash(11);
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDataPropertyDomainAxiom axiom) {
        addValueToHash(13);
        axiom.getProperty().accept(this);
        axiom.getDomain().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDataPropertyRangeAxiom axiom) {
        addValueToHash(17);
        axiom.getProperty().accept(this);
        axiom.getRange().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLSubDataPropertyOfAxiom axiom) {
        addValueToHash(19);
        axiom.getSubProperty().accept(this);
        axiom.getSuperProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDeclarationAxiom axiom) {
        addValueToHash(23);
        axiom.getEntity().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDifferentIndividualsAxiom axiom) {
        addValueToHash(29);
        visitStream(axiom.individuals());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDisjointClassesAxiom axiom) {
        addValueToHash(31);
        visitStream(axiom.classExpressions());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDisjointDataPropertiesAxiom axiom) {
        addValueToHash(37);
        visitStream(axiom.properties());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDisjointObjectPropertiesAxiom axiom) {
        addValueToHash(41);
        visitStream(axiom.properties());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLDisjointUnionAxiom axiom) {
        addValueToHash(43);
        axiom.getOWLClass().accept(this);
        visitStream(axiom.classExpressions());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLAnnotationAssertionAxiom axiom) {
        addValueToHash(47);
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getValue().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLEquivalentClassesAxiom axiom) {
        addValueToHash(53);
        visitStream(axiom.classExpressions());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLEquivalentDataPropertiesAxiom axiom) {
        addValueToHash(59);
        visitStream(axiom.properties());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLEquivalentObjectPropertiesAxiom axiom) {
        addValueToHash(61);
        visitStream(axiom.properties());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLFunctionalDataPropertyAxiom axiom) {
        addValueToHash(67);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLFunctionalObjectPropertyAxiom axiom) {
        addValueToHash(71);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLInverseFunctionalObjectPropertyAxiom axiom) {
        addValueToHash(79);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLInverseObjectPropertiesAxiom axiom) {
        addValueToHash(83);
        hashCode = hashCode * MULT + axiom.getFirstProperty().hashCode() + axiom.getSecondProperty().hashCode();
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLIrreflexiveObjectPropertyAxiom axiom) {
        addValueToHash(89);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLNegativeDataPropertyAssertionAxiom axiom) {
        addValueToHash(97);
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLNegativeObjectPropertyAssertionAxiom axiom) {
        addValueToHash(101);
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLObjectPropertyAssertionAxiom axiom) {
        addValueToHash(103);
        axiom.getSubject().accept(this);
        axiom.getProperty().accept(this);
        axiom.getObject().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLSubPropertyChainOfAxiom axiom) {
        addValueToHash(107);
        visitStream(axiom.getPropertyChain());
        axiom.getSuperProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLObjectPropertyDomainAxiom axiom) {
        addValueToHash(109);
        axiom.getProperty().accept(this);
        axiom.getDomain().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLObjectPropertyRangeAxiom axiom) {
        addValueToHash(113);
        axiom.getProperty().accept(this);
        axiom.getRange().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLSubObjectPropertyOfAxiom axiom) {
        addValueToHash(127);
        axiom.getSubProperty().accept(this);
        axiom.getSuperProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLReflexiveObjectPropertyAxiom axiom) {
        addValueToHash(131);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLSameIndividualAxiom axiom) {
        addValueToHash(137);
        visitStream(axiom.individuals());
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLSymmetricObjectPropertyAxiom axiom) {
        addValueToHash(149);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLTransitiveObjectPropertyAxiom axiom) {
        addValueToHash(151);
        axiom.getProperty().accept(this);
        visitStream(axiom.annotations());
    }

    @Override
    public void visit(OWLClass ce) {
        addValueToHash(157);
        ce.getIRI().accept(this);
    }

    @Override
    public void visit(OWLDataAllValuesFrom ce) {
        addValueToHash(163);
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLDataExactCardinality ce) {
        addValueToHash(167);
        ce.getProperty().accept(this);
        addValueToHash(ce.getCardinality());
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLDataMaxCardinality ce) {
        addValueToHash(173);
        ce.getProperty().accept(this);
        addValueToHash(ce.getCardinality());
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLDataMinCardinality ce) {
        addValueToHash(179);
        ce.getProperty().accept(this);
        addValueToHash(ce.getCardinality());
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLDataSomeValuesFrom ce) {
        addValueToHash(181);
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLDataHasValue ce) {
        addValueToHash(191);
        addValueToHash(hashCode(ce.getProperty()));
        addValueToHash(hashCode(ce.getFiller()));
    }

    @Override
    public void visit(OWLObjectAllValuesFrom ce) {
        addValueToHash(193);
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLObjectComplementOf ce) {
        addValueToHash(197);
        ce.getOperand().accept(this);
    }

    @Override
    public void visit(OWLObjectExactCardinality ce) {
        addValueToHash(199);
        ce.getProperty().accept(this);
        addValueToHash(ce.getCardinality());
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLObjectIntersectionOf ce) {
        addValueToHash(211);
        visitStream(ce.operands());
    }

    @Override
    public void visit(OWLObjectMaxCardinality ce) {
        addValueToHash(223);
        ce.getProperty().accept(this);
        addValueToHash(ce.getCardinality());
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLObjectMinCardinality ce) {
        addValueToHash(227);
        ce.getProperty().accept(this);
        addValueToHash(ce.getCardinality());
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLObjectOneOf ce) {
        addValueToHash(229);
        visitStream(ce.individuals());
    }

    @Override
    public void visit(OWLObjectHasSelf ce) {
        addValueToHash(233);
        ce.getProperty().accept(this);
    }

    @Override
    public void visit(OWLObjectSomeValuesFrom ce) {
        addValueToHash(239);
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLObjectUnionOf ce) {
        addValueToHash(241);
        visitStream(ce.operands());
    }

    @Override
    public void visit(OWLObjectHasValue ce) {
        addValueToHash(251);
        ce.getProperty().accept(this);
        ce.getFiller().accept(this);
    }

    @Override
    public void visit(OWLDataComplementOf node) {
        addValueToHash(257);
        node.getDataRange().accept(this);
    }

    @Override
    public void visit(OWLDataOneOf node) {
        addValueToHash(263);
        visitStream(node.values());
    }

    @Override
    public void visit(OWLDatatype node) {
        addValueToHash(269);
        node.getIRI().accept(this);
    }

    @Override
    public void visit(OWLDatatypeRestriction node) {
        addValueToHash(271);
        node.getDatatype().accept(this);
        visitStream(node.facetRestrictions());
    }

    @Override
    public void visit(OWLFacetRestriction node) {
        addValueToHash(563);
        addValueToHash(node.getFacet().hashCode());
        node.getFacetValue().accept(this);
    }

    @Override
    public void visit(OWLDataProperty property) {
        addValueToHash(283);
        property.getIRI().accept(this);
    }

    @Override
    public void visit(OWLObjectProperty property) {
        addValueToHash(293);
        property.getIRI().accept(this);
    }

    @Override
    public void visit(OWLObjectInverseOf property) {
        addValueToHash(307);
        property.getInverse().accept(this);
    }

    @Override
    public void visit(OWLNamedIndividual individual) {
        addValueToHash(311);
        individual.getIRI().accept(this);
    }

    @Override
    public void visit(SWRLRule rule) {
        addValueToHash(631);
        visitStream(rule.body());
        visitStream(rule.head());
    }

    @Override
    public void visit(SWRLClassAtom node) {
        addValueToHash(641);
        node.getArgument().accept(this);
        node.getPredicate().accept(this);
    }

    @Override
    public void visit(SWRLDataRangeAtom node) {
        addValueToHash(643);
        node.getArgument().accept(this);
        node.getPredicate().accept(this);
    }

    @Override
    public void visit(SWRLObjectPropertyAtom node) {
        addValueToHash(647);
        node.getFirstArgument().accept(this);
        node.getSecondArgument().accept(this);
        node.getPredicate().accept(this);
    }

    @Override
    public void visit(SWRLDataPropertyAtom node) {
        addValueToHash(653);
        node.getFirstArgument().accept(this);
        node.getSecondArgument().accept(this);
        node.getPredicate().accept(this);
    }

    @Override
    public void visit(SWRLBuiltInAtom node) {
        addValueToHash(659);
        visitStream(node.allArguments());
        node.getPredicate().accept(this);
    }

    @Override
    public void visit(SWRLVariable node) {
        addValueToHash(661);
        node.getIRI().accept(this);
    }

    @Override
    public void visit(SWRLIndividualArgument node) {
        addValueToHash(677);
        node.getIndividual().accept(this);
    }

    @Override
    public void visit(SWRLLiteralArgument node) {
        addValueToHash(683);
        node.getLiteral().accept(this);
    }

    @Override
    public void visit(SWRLDifferentIndividualsAtom node) {
        addValueToHash(797);
        node.getFirstArgument().accept(this);
        node.getSecondArgument().accept(this);
    }

    @Override
    public void visit(SWRLSameIndividualAtom node) {
        addValueToHash(811);
        int i = node.getFirstArgument().hashCode();
        addValueToHash(i);
        node.getSecondArgument().accept(this);
    }

    public void addValueToHash(int i) {
        hashCode = hashCode * MULT + i;
    }

    @Override
    public void visit(OWLHasKeyAxiom axiom) {
        addValueToHash(821);
        axiom.getClassExpression().accept(this);
        visitStream(axiom.propertyExpressions());
    }

    @Override
    public void visit(OWLAnnotationPropertyDomainAxiom axiom) {
        addValueToHash(823);
        axiom.getProperty().accept(this);
        axiom.getDomain().accept(this);
    }

    @Override
    public void visit(OWLAnnotationPropertyRangeAxiom axiom) {
        addValueToHash(827);
        axiom.getProperty().accept(this);
        axiom.getRange().accept(this);
    }

    @Override
    public void visit(OWLSubAnnotationPropertyOfAxiom axiom) {
        addValueToHash(829);
        axiom.getSubProperty().accept(this);
        axiom.getSuperProperty().accept(this);
    }

    @Override
    public void visit(OWLDataIntersectionOf node) {
        addValueToHash(839);
        visitStream(node.operands());
    }

    @Override
    public void visit(OWLDataUnionOf node) {
        addValueToHash(853);
        visitStream(node.operands());
    }

    @Override
    public void visit(OWLAnnotationProperty property) {
        addValueToHash(857);
        property.getIRI().accept(this);
    }

    @Override
    public void visit(OWLAnonymousIndividual individual) {
        addValueToHash(859);
        addValueToHash(individual.getID().hashCode());
    }

    @Override
    public void visit(IRI iri) {
        addValueToHash(863);
        addValueToHash(iri.toURI().hashCode());
    }

    @Override
    public void visit(OWLAnnotation node) {
        addValueToHash(877);
        node.getProperty().accept(this);
        node.getValue().accept(this);
    }

    @Override
    public void visit(OWLDatatypeDefinitionAxiom axiom) {
        addValueToHash(897);
        axiom.getDatatype().accept(this);
        axiom.getDataRange().accept(this);
    }

    @Deprecated
    private void visitStream(Collection<? extends OWLObject> collection) {
        collection.forEach(o -> o.accept(this));
    }

    private void visitStream(Stream<? extends OWLObject> collection) {
        collection.forEach(o -> o.accept(this));
    }
}
