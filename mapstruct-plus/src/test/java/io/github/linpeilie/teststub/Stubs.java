package io.github.linpeilie.teststub;

import io.github.linpeilie.BaseMapper;
import io.github.linpeilie.BaseMapMapper;
import java.util.Map;

/**
 * 清单驱动加载测试的手写桩（不经过注解处理器），集中收口以减少测试文件数。
 * 模拟生成的 mapper 形态：接口经一层继承间接 extends BaseMapper / BaseMapMapper，
 * Impl 只实现 mapper 接口，由 DefaultConnectionFactory 递归解析泛型定位。
 * 嵌套类的二进制名（Stubs$XxxMapperImpl）与 MapStruct 默认实现命名
 * （接口二进制名 + Impl）一致，Mappers.getMapper 可正常定位
 *
 * @author shanhongyu
 */
public final class Stubs {

    private Stubs() {
    }

    public static class StubUser {

        private final String name;

        public StubUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public static class StubUserDto {

        private String name;

        public StubUserDto() {
        }

        public StubUserDto(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public interface StubUserMapper extends BaseMapper<StubUser, StubUserDto> {

    }

    public static class StubUserMapperImpl implements StubUserMapper {

        @Override
        public StubUserDto convert(StubUser source) {
            if (source == null) {
                return null;
            }
            return new StubUserDto(source.getName());
        }

        @Override
        public StubUserDto convert(StubUser source, StubUserDto target) {
            if (source == null) {
                return target;
            }
            target.setName(source.getName());
            return target;
        }

    }

    public static class StubMapModel {

        private String str;

        private Integer count;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

    }

    public interface StubMapModelMapper extends BaseMapMapper<StubMapModel> {

    }

    public static class StubMapModelMapperImpl implements StubMapModelMapper {

        @Override
        public StubMapModel convert(Map<String, Object> map) {
            if (map == null) {
                return null;
            }
            StubMapModel model = new StubMapModel();
            Object str = map.get("str");
            if (str != null) {
                model.setStr(str.toString());
            }
            Object count = map.get("count");
            if (count != null) {
                model.setCount(Integer.valueOf(count.toString()));
            }
            return model;
        }

    }

}
