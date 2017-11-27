package com.topscore.order.dispatcher.mock;

import com.topscore.order.dispatcher.domain.OrderMessage;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.topscore.order.dispatcher.domain.OrderMessage.Type.*;

public class TmallMock {

    private static String content = "{\"trade\":[{\"tid\":34915421540937721,\"tid_str\":\"34915421540937721\",\"status\":\"WAIT_SELLER_SEND_GOODS\",\"type\":\"fixed\",\"seller_nick\":\"tigrisso旗舰店\",\"buyer_nick\":\"星辰无心陨落\",\"created\":\"2017-07-31 23:50:43\",\"modified\":\"2017-07-31 23:50:52\",\"encrypt_alipay_id\":\"2088102007474881\",\"adjust_fee\":\"0.00\",\"alipay_id\":2088102007474881,\"alipay_no\":\"2017073121001001880291694966\",\"alipay_point\":0,\"available_confirm_fee\":\"1198.00\",\"buyer_alipay_no\":\"rccfamily@126.com\",\"buyer_area\":\"上海上海电信\",\"buyer_cod_fee\":\"0.00\",\"buyer_email\":\"rccfamily@126.com\",\"buyer_ip\":\"MTAxLjIyOS4xNjEuMjY=\",\"buyer_obtain_point_fee\":0,\"buyer_rate\":false,\"cod_fee\":\"0.00\",\"cod_status\":\"NEW_CREATED\",\"commission_fee\":\"0.00\",\"discount_fee\":\"100.00\",\"has_post_fee\":true,\"is_3D\":false,\"is_brand_sale\":false,\"is_daixiao\":false,\"is_force_wlb\":false,\"is_sh_ship\":false,\"is_lgtype\":false,\"is_part_consign\":false,\"is_wt\":false,\"orders\":{\"order\":[{\"adjust_fee\":\"0.00\",\"buyer_rate\":false,\"cid\":50012027,\"discount_fee\":\"599.00\",\"divide_order_fee\":\"645.15\",\"is_daixiao\":false,\"is_oversold\":false,\"num\":1,\"num_iid\":545014675775,\"oid\":34915421541937721,\"order_from\":\"WAP,WAP\",\"outer_iid\":\"TA87117-16\",\"outer_sku_id\":\"6901625793473\",\"part_mjz_discount\":\"53.85\",\"payment\":\"699.00\",\"pic_path\":\"https://img.alicdn.com/bao/uploaded/i4/1574853209/TB25LPSeORnpuFjSZFCXXX2DXXa_!!1574853209.jpg\",\"price\":\"1298.00\",\"refund_status\":\"NO_REFUND\",\"seller_rate\":false,\"seller_type\":\"B\",\"sku_id\":\"3285583732624\",\"sku_properties_name\":\"颜色分类:浅绿色;尺码:35\",\"snapshot_url\":\"m:34915421541937721_1\",\"status\":\"WAIT_SELLER_SEND_GOODS\",\"title\":\"tigrisso蹀愫2017春新品TA87117-16蝴蝶结低跟方头小V口浅口单鞋\",\"total_fee\":\"699.00\"},{\"adjust_fee\":\"0.00\",\"buyer_rate\":false,\"cid\":50012027,\"discount_fee\":\"799.00\",\"divide_order_fee\":\"552.85\",\"is_daixiao\":false,\"is_oversold\":false,\"num\":1,\"num_iid\":544507024864,\"oid\":34915421542937721,\"order_from\":\"WAP,WAP\",\"outer_iid\":\"TA87108-19\",\"outer_sku_id\":\"6901625787847\",\"part_mjz_discount\":\"46.15\",\"payment\":\"599.00\",\"pic_path\":\"https://img.alicdn.com/bao/uploaded/i1/1574853209/TB2ZjCYdNhmpuFjSZFyXXcLdFXa_!!1574853209.jpg\",\"price\":\"1398.00\",\"refund_status\":\"NO_REFUND\",\"seller_rate\":false,\"seller_type\":\"B\",\"sku_id\":\"3445792151608\",\"sku_properties_name\":\"颜色分类:杏色;尺码:35\",\"snapshot_url\":\"m:34915421542937721_1\",\"status\":\"WAIT_SELLER_SEND_GOODS\",\"title\":\"tigrisso蹀愫2017春新品TA87108-19细高跟小V口金属尖头浅口单鞋\",\"total_fee\":\"599.00\"}]},\"pay_time\":\"2017-07-31 23:50:52\",\"payment\":\"1198.00\",\"pcc_af\":0,\"point_fee\":0,\"post_fee\":\"0.00\",\"promotion_details\":{\"promotion_detail\":[{\"discount_fee\":\"599.00\",\"id\":34915421541937721,\"promotion_desc\":\"换季促销:省599.00元\",\"promotion_id\":\"viptool20120502-3873210331_21005025731\",\"promotion_name\":\"换季促销\"},{\"discount_fee\":\"0.00\",\"id\":34915421540937721,\"promotion_desc\":\"店铺VIP包邮:省0.00元\",\"promotion_id\":\"tmallShopMemberRight-112300767_855630837\",\"promotion_name\":\"店铺VIP包邮\"},{\"discount_fee\":\"100.00\",\"id\":34915421540937721,\"promotion_desc\":\"新品尝鲜100:省100.00元\",\"promotion_id\":\"shopbonus-3900980808_21028095525-289422556383\",\"promotion_name\":\"新品尝鲜100\"},{\"discount_fee\":\"799.00\",\"id\":34915421542937721,\"promotion_desc\":\"换季促销:省799.00元\",\"promotion_id\":\"viptool20120502-3873210331_21008700656\",\"promotion_name\":\"换季促销\"}]},\"real_point_fee\":0,\"received_payment\":\"0.00\",\"receiver_address\":\"虹梅路街道田林路142号H楼801室\",\"receiver_city\":\"上海市\",\"receiver_country\":\"\",\"receiver_district\":\"徐汇区\",\"receiver_mobile\":\"13916316430\",\"receiver_name\":\"任晨辰\",\"receiver_state\":\"上海\",\"receiver_town\":\"虹梅路街道\",\"receiver_zip\":\"200030\",\"seller_alipay_no\":\"tigrisso@163.com\",\"seller_can_rate\":false,\"seller_cod_fee\":\"0.00\",\"seller_email\":\"tigrisso@163.com\",\"seller_flag\":0,\"seller_mobile\":\"13719385025\",\"seller_name\":\"广州天创时尚鞋业股份有限公司\",\"seller_rate\":false,\"service_tags\":{\"logistics_tag\":[{\"logistic_service_tag_list\":{\"logistic_service_tag\":[{\"service_tag\":\"origAreaId=310104;lgType=-4\",\"service_type\":\"FAST\"}]},\"order_id\":\"34915421540937721\"}]},\"shipping_type\":\"express\",\"snapshot_url\":\"m:34915421540937721_1\",\"title\":\"tigrisso旗舰店\",\"total_fee\":\"2696.00\",\"trade_from\":\"WAP,WAP\"}]}";

    public static List<OrderMessage> getOrderMessages(int count) {
        List<OrderMessage> list = new ArrayList<>(count);
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i++ < count; ) {
            OrderMessage message = new OrderMessage();
            message.setId(getId());
            message.setType(getType());
            message.setContent(content);
            message.setCreatedTime(now);
            list.add(message);
        }
        return list;
    }

    public static List<OrderMessage> getOrderMessages() {
        return getOrderMessages(10);
    }

    private static String getId() {
        return RandomUtils.nextLong(10000000000000000L, 99999999999999999L) + "";
    }

    private static OrderMessage.Type getType() {
        OrderMessage.Type[] types = new OrderMessage.Type[]{TMALL, TAOBAO, JD, VIP};
        return types[RandomUtils.nextInt(0, 4)];
    }
}
