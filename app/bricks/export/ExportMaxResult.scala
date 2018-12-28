package bricks.export

import com.pharbers.builder.search.MaxResultFacade
import com.pharbers.jsonapi.json.circe.CirceJsonapiSupport
import com.pharbers.jsonapi.model
import module.export.exportmaxresult
import play.api.mvc.Request
import com.pharbers.macros._
import com.pharbers.macros.convert.jsonapi.JsonapiMacro._
import com.pharbers.pattern.frame._

/**
  * @ ProjectName pharbers_max_client.bricks.export.ExportMaxResult
  * @ author jeorch
  * @ date 18-10-15
  * @ Description: TODO
  */
case class ExportMaxResult()(implicit val rq: Request[model.RootObject])
    extends Brick with CirceJsonapiSupport {

    override val brick_name: String = "get sample check selecter data"

    var emr_data: exportmaxresult = new exportmaxresult()

    override def prepare: Unit = emr_data = formJsonapi[exportmaxresult](rq.body)

    override def exec: Unit = {
        val company_id = emr_data.company_id
        val ym = emr_data.ym
        val market = emr_data.market
        val job_id = emr_data.job_id
        val mrf = new MaxResultFacade
        val resultPath = mrf.export(company_id,ym,market,job_id)
        emr_data.result_path = resultPath
    }

    override def goback: model.RootObject = toJsonapi(emr_data)
}
