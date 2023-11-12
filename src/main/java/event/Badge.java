package event;

public enum Badge {
    SANTA("산타", 20_000), TREE("트리", 10_000), STAR("별", 5_000), NO_BADGE("없음", 0);

    private String name;
    private int amount;
    Badge(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getBadgeName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
