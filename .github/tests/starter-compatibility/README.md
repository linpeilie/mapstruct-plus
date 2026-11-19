# Starter compatibility fixture

This independent Maven application is used only by `test-spring-boot.yml`; it is outside the
release reactor. Its Boot BOM selects the runtime versions while the starter and processor
are built from the current checkout under a validation-only snapshot version.

| Java | Boot | Starter |
| --- | --- | --- |
| 8 | 2.7.18 | `mapstruct-plus-spring-boot-starter` |
| 17 | 3.5.8 | `mapstruct-plus-spring-boot-starter` |
| 17 | 4.0.3 | `mapstruct-plus-spring-boot4-starter` |
| 25 | 4.1.2 | `mapstruct-plus-spring-boot4-starter` |

Tests load auto-configuration without scanning the generated mappers, convert in both directions,
check the actual Boot/Spring versions, and reject a processor on the application classpath.
A missing test suite fails the build. This workflow does not publish artifacts or use release secrets.
