package com.example.tacocloud.data

import com.example.tacocloud.Taco
import com.example.tacocloud.TacoOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Types
import java.util.*

@Repository
class JdbcOrderRepository @Autowired constructor(
    private val jdbcOperations: JdbcOperations,
) : OrderRepository {
    override fun save(order: TacoOrder): TacoOrder {
        order.placedAt = Date()
        order.saveToDb()
        order.tacos.forEachIndexed { index, taco ->
            saveTaco(order.id, index, taco)
        }
        return order
    }

    private fun TacoOrder.saveToDb() {
        val keyHolder = GeneratedKeyHolder()
        jdbcOperations.update(
            prepareSaveStatementCreator(this),
            keyHolder
        )
        this.id = keyHolder.key!!.toLong()
    }

    private fun saveTaco(orderId: Long, orderKey: Int, taco: Taco): Long {
        taco.createdAd = Date()
        taco.saveToDb(orderId, orderKey)
        return taco.id
    }

    private fun Taco.saveToDb(orderId: Long, orderKey: Int) {
        val keyHolder = GeneratedKeyHolder()
        jdbcOperations.update(
            prepareSaveTacoStatementCreator(this, orderId, orderKey),
            keyHolder
        )
        this.id = keyHolder.key!!.toLong()
        ingredients.forEachIndexed { index, ingredient ->
            jdbcOperations.update(
                "insert into Ingredient_Ref (ingredient, taco, taco_key) " +
                        "values (?, ?, ?)",
                ingredient.id, this.id, index,
            )
        }
    }
}

private fun prepareSaveStatementCreator(order: TacoOrder): PreparedStatementCreator {
    val pscf = PreparedStatementCreatorFactory(
        "insert into Taco_Order " +
                "(delivery_Name, delivery_Street, delivery_City, " +
                "delivery_State, delivery_Zip, cc_number, " +
                "cc_expiration, cc_cvv, placed_at) " +
                "values (?,?,?,?,?,?,?,?,?)",
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
    )
    pscf.setReturnGeneratedKeys(true)

    return with(order) {
        pscf.newPreparedStatementCreator(
            listOf(
                deliveryName, deliveryStreet, deliveryCity,
                deliveryState, deliveryZip, ccNumber,
                ccExpiration, ccCVV, placedAt,
            )
        )
    }
}

private fun prepareSaveTacoStatementCreator(taco: Taco, orderId: Long, orderKey: Int): PreparedStatementCreator {
    val pscf = PreparedStatementCreatorFactory(
        "insert into Taco " +
                "(name, taco_order, taco_order_key, created_at) " +
                "values (?, ?, ?, ?)",
        Types.VARCHAR, Types.LONGVARCHAR, Types.LONGVARCHAR, Types.TIMESTAMP,
    )
    pscf.setReturnGeneratedKeys(true)

    return pscf.newPreparedStatementCreator(
        listOf(
            taco.name, orderId, orderKey, taco.createdAd
        )
    )
}

