package me.zhenxin.aida.enums.page;

/**
 * 选择APK类型枚举
 */
public enum EnumChooseApkType {
    选择应用(0),
    选择文件(1),
    ;

    private final int position;

    EnumChooseApkType(int pos) {
        position = pos;
    }

    public static EnumChooseApkType getPage(int position) {
        return EnumChooseApkType.values()[position];
    }

    public static int size() {
        return EnumChooseApkType.values().length;
    }

    public static String[] getPageNames() {
        EnumChooseApkType[] pages = EnumChooseApkType.values();
        String[] pageNames = new String[pages.length];
        for (int i = 0; i < pages.length; i++) {
            pageNames[i] = pages[i].name();
        }
        return pageNames;
    }

    public int getPosition() {
        return position;
    }
}
