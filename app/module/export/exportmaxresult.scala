package module.export

import com.pharbers.macros.api.commonEntity
import com.pharbers.macros.common.connecting.ToStringMacro

/**
  * @ ProjectName pharbers_max_client.module.export.exportmaxresult
  * @ author jeorch
  * @ date 18-10-15
  * @ Description: TODO
  */
@ToStringMacro
class exportmaxresult extends commonEntity {
    var job_id: String = ""
    var company_id: String = ""
    var ym: String = ""
    var market: String = ""
    var result_path: String = ""
}
