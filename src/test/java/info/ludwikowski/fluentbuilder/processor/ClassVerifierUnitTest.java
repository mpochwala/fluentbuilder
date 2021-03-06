/*
 * Created on 18-03-2013 18:37:00 by Andrzej Ludwikowski
 */
/*
 * Modified by Jan van Esdonk for BLUECARAT AG
 */
package info.ludwikowski.fluentbuilder.processor;

import static info.ludwikowski.fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_EMBEDDABLE;
import static info.ludwikowski.fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_ENTITY;
import static info.ludwikowski.fluentbuilder.processor.ProcessorContext.JAVAX_PERSISTENCE_MAPPEDSUPERCLASS;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import info.ludwikowski.fluentbuilder.annotation.GenerateBuilder;

import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

/**
 * @author Andrzej Ludwikowski
 */

public class ClassVerifierUnitTest {

	private ClassVerifier classVerifier;
	private ProcessorContext context;


	@Before
	public void setUp() {

		context = mock(ProcessorContext.class);
		classVerifier = new ClassVerifier(context);
	}

	@Test
	public void shouldReturnTrueForElementAnnotatedWithProcessorAnnotation() {

		// given
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);
		String type = GenerateBuilder.class.getCanonicalName();

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(type);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldReturnTrueForElementAnnotatedWithEmbeddableJPAAnnotation() {
		// given
		final String jpaAnnotation = JAVAX_PERSISTENCE_EMBEDDABLE;
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(jpaAnnotation);
		given(context.isAcceptJavaPersistenceAnnotations()).willReturn(true);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldReturnTrueForElementAnnotatedWithEntityJPAAnnotation() {
		// given
		final String jpaAnnotation = JAVAX_PERSISTENCE_ENTITY;
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(jpaAnnotation);
		given(context.isAcceptJavaPersistenceAnnotations()).willReturn(true);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldReturnTrueForElementAnnotatedWithMappedSuperClassJPAAnnotation() {
		// given
		final String jpaAnnotation = JAVAX_PERSISTENCE_MAPPEDSUPERCLASS;
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(jpaAnnotation);
		given(context.isAcceptJavaPersistenceAnnotations()).willReturn(true);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldReturnFalseForOtherAnnotations() {

		// given
		Element element = mock(Element.class);
		AnnotationMirror annotationMirror = mock(AnnotationMirror.class, Mockito.RETURNS_DEEP_STUBS);
		String type = "something.GenerateBuilder";

		List<AnnotationMirror> annotationMirrors = Lists.newArrayList(annotationMirror);
		doReturn(annotationMirrors).when(element).getAnnotationMirrors();
		given(annotationMirror.getAnnotationType().toString()).willReturn(type);

		// when
		boolean result = classVerifier.generateBuilder(element);

		// then
		assertThat(result).isFalse();
	}
}
