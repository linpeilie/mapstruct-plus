package io.github.linpeilie;

import io.github.linpeilie.me.callbacks.ClassContainingCallbacks;
import io.github.linpeilie.me.callbacks.Invocation;
import io.github.linpeilie.me.callbacks.Source;
import io.github.linpeilie.me.callbacks.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class CallbackMethodTest {

    @Autowired
    private Converter converter;

    @BeforeEach
    public void reset() {
        ClassContainingCallbacks.reset();
        io.github.linpeilie.me.callbacks.BaseMapper.reset();
    }

    @Test
    public void callbackMethodsForBeanMappingCalled() {
        converter.convert(createSource(), Target.class);

        assertBeanMappingInvocations(ClassContainingCallbacks.getInvocations());
        assertBeanMappingInvocations(io.github.linpeilie.me.callbacks.BaseMapper.getInvocations());
    }

    @Test
    public void callbackMethodsForBeanMappingWithResultParamCalled() {
        converter.convert(createSource(), createEmptyTarget());

        assertBeanMappingInvocations(ClassContainingCallbacks.getInvocations());
        assertBeanMappingInvocations(io.github.linpeilie.me.callbacks.BaseMapper.getInvocations());
    }

    private void assertBeanMappingInvocations(List<Invocation> invocations) {
        Source source = createSource();
        Target target = createResultTarget();
        Target emptyTarget = createEmptyTarget();

        assertThat(invocations).isEqualTo(beanMappingInvocationList(source, target, emptyTarget));
    }

    private List<Invocation> beanMappingInvocationList(Object source, Object target, Object emptyTarget) {
        List<Invocation> invocations = new ArrayList<>();

        invocations.addAll(allBeforeMappingMethods(source, emptyTarget, Target.class));
        invocations.addAll(allAfterMappingMethods(source, target, Target.class));

        return invocations;
    }

    private List<Invocation> allAfterMappingMethods(Object source, Object target, Class<?> targetClass) {
        return new ArrayList<>(Arrays.asList(
            new Invocation("noArgsAfterMapping"),
            new Invocation("withSourceAfterMapping", source),
            new Invocation("withSourceAsObjectAfterMapping", source),
            new Invocation("withSourceAndTargetAfterMapping", source, target),
            new Invocation("withTargetAfterMapping", target),
            new Invocation("withTargetAsObjectAfterMapping", target),
            new Invocation("withTargetAndTargetTypeAfterMapping", target, targetClass)));
    }

    private List<Invocation> allBeforeMappingMethods(Object source, Object emptyTarget, Class<?> targetClass) {
        return new ArrayList<>(Arrays.asList(
            new Invocation("noArgsBeforeMapping"),
            new Invocation("withSourceBeforeMapping", source),
            new Invocation("withSourceAsObjectBeforeMapping", source),
            new Invocation("withSourceAndTargetTypeBeforeMapping", source, targetClass),
            new Invocation("withSourceAndTargetBeforeMapping", source, emptyTarget),
            new Invocation("withTargetBeforeMapping", emptyTarget),
            new Invocation("withTargetAsObjectBeforeMapping", emptyTarget)));
    }

    private Source createSource() {
        Source source = new Source();
        source.setFoo("foo");
        return source;
    }

    private Target createEmptyTarget() {
        return new Target();
    }

    private Target createResultTarget() {
        Target target = createEmptyTarget();
        target.setFoo("foo");
        return target;
    }

}
