package com.wootion.config.mybatisconfig;

/**
 * mybatis配置属性
 * @author ZhangXiaoXiang
 * @date 2018-03-10
 */
public class MybatisProperties {

    public MybatisProperties() {
        page = new PageHelperProperties();
    }

    private String mapperLocations;
    private String typeAliasesPackage;
    private String basePackage;
    private String typeHandlersPackage;
    private PageHelperProperties page;

    public PageHelperProperties getPage() {
        return page;
    }

    public void setPage(PageHelperProperties page) {
        this.page = page;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTypeHandlersPackage() {
        return typeHandlersPackage;
    }

    public void setTypeHandlersPackage(String typeHandlersPackage) {
        this.typeHandlersPackage = typeHandlersPackage;
    }

    public class PageHelperProperties {

        private String dialect;
        private String offsetAsPageNum;
        private String rowBoundsWithCount;
        private String pageSizeZero;
        private String reasonable;
        private String supportMethodsArguments;
        private String returnPageInfo;

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public String getOffsetAsPageNum() {
            return offsetAsPageNum;
        }

        public void setOffsetAsPageNum(String offsetAsPageNum) {
            this.offsetAsPageNum = offsetAsPageNum;
        }

        public String getRowBoundsWithCount() {
            return rowBoundsWithCount;
        }

        public void setRowBoundsWithCount(String rowBoundsWithCount) {
            this.rowBoundsWithCount = rowBoundsWithCount;
        }

        public String getPageSizeZero() {
            return pageSizeZero;
        }

        public void setPageSizeZero(String pageSizeZero) {
            this.pageSizeZero = pageSizeZero;
        }

        public String getReasonable() {
            return reasonable;
        }

        public void setReasonable(String reasonable) {
            this.reasonable = reasonable;
        }

        public String getSupportMethodsArguments() {
            return supportMethodsArguments;
        }

        public void setSupportMethodsArguments(String supportMethodsArguments) {
            this.supportMethodsArguments = supportMethodsArguments;
        }

        public String getReturnPageInfo() {
            return returnPageInfo;
        }

        public void setReturnPageInfo(String returnPageInfo) {
            this.returnPageInfo = returnPageInfo;
        }
    }
}