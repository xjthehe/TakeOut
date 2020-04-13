package ndk.pax.com.paxtakeout.model.bean

/**
 * Created by Administrator on 2020/4/13.
 */
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
@DatabaseTable(tableName = "t_address")class RecepitAddressBean(){
    @DatabaseField(generatedId = true) var id:Int=0
    @DatabaseField(columnName = "username")var username:String=""
    @DatabaseField(columnName = "sex")var sex:String=""
    @DatabaseField(columnName = "phone")var phone:String=""
    @DatabaseField(columnName = "phoneOther")var phoneOther:String=""
    @DatabaseField(columnName = "address")var address:String=""
    @DatabaseField(columnName = "detailaddress")var detailaddress:String=""
    @DatabaseField(columnName = "label")var label:String=""
    @DatabaseField(columnName = "userId")var userId:String="38"


    constructor(id: Int, username: String, sex: String, phone: String,
                phoneOther: String, address: String, detailaddress: String, label: String, userId: String):this() {
        this.id = id
        this.username = username
        this.sex = sex
        this.phone = phone
        this.phoneOther = phoneOther
        this.address = address
        this.detailaddress = detailaddress
        this.label = label
        this.userId = userId
    }
}
