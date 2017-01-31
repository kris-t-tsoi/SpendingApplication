package kristytsoi.spendingapplication;

/**
 * Created by Kristy on 31/01/2017.
 */
public class MenuListItemDetails {

    String detail;
    String valueText;

    public MenuListItemDetails(String detail, double valueText) {
        this.detail = detail;
        this.valueText = "$" + String.format("%.2f",valueText);
    }


}
