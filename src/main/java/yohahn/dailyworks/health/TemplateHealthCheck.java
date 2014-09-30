package yohahn.dailyworks.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * @author yohahn.kim
 * @since 9/6/14 9:14 PM
 */
public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(this.template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
