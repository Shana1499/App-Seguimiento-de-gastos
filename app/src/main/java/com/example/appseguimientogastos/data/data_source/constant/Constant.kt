package com.example.appseguimientogastos.data.data_source.constant
import kotlinx.serialization.json.Json

class Constant {
    companion object {
        val dot: Char = '.'
        val numberList = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        )
        const val MAX_DECIMAL_PLACES_PRICE = 2
        const val NAME = "appseguimientogastos"
        const val DEBUG = "debug"
        const val YUMI_WIDTH = 720
        const val YUMI_HEIGHT = 1280

        const val EMPTY_STRING = ""

        const val LOG_TAG = "ASCOTT_HOTEL_LOG"

        const val KEY_LOGIN_CLIENT_ID = "KEY_LOGIN_CLIENT_ID"
        const val KEY_LOGIN_CLIENT_SECRET = "KEY_LOGIN_CLIENT_SECRET"
        const val KEY_LOGIN_CLIENT_NAME = "KEY_LOGIN_CLIENT_NAME"
        const val KEY_LOGIN_PROPERTY_ID = "KEY_LOGIN_PROPERTY_ID"
        const val KEY_LOGIN_TERMINAL_ID = "KEY_LOGIN_TERMINAL_ID"
        const val KEY_LOGIN_PAYMENT_TERMINAL_NO = "KEY_LOGIN_PAYMENT_TERMINAL_NO"
        const val KEY_LOGIN_BASE_URL = "KEY_LOGIN_BASE_URL"
        const val KEY_LOGIN_DEFAULT_CURRENCY_NUM = "KEY_LOGIN_DEFAULT_CURRENCY_NUM"
        const val KEY_LOGIN_ACCESS_TOKEN = "KEY_LOGIN_ACCESS_TOKEN"
        const val KEY_LOGIN_TOKEN_TYPE = "KEY_LOGIN_TOKEN_TYPE"
        const val KEY_LOGIN_EXPIRES_IN = "KEY_LOGIN_EXPIRES_IN"
        const val KEY_SERVER_INTERACTIONS = "KEY_SERVER_INTERACTIONS"

        enum class BuildType {
            DEBUG, RELEASE
        }
        fun preferencesName(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> "${NAME}_${DEBUG}"
            BuildType.RELEASE -> NAME
        }

      /*  val DEFAULT_CURRENCY_LIST: List<CurrencyIso4217> = CurrencyIso4217.values().toList()
        val DEFAULT_CURRENCY: CurrencyIso4217 = DEFAULT_CURRENCY_LIST.first()
        val DEFAULT_LANGUAGE: LanguageISO639 = LanguageISO639.ENGLISH*/

        const val WPI_VERSION: Int = 1

        const val HEADER_KEY_CLIENT_ID = "client_id"
        const val HEADER_KEY_TOKEN = "Authorization"
        const val HEADER_KEY_CLIENT_SECRET = "client_secret"
        const val HEADER_KEY_CLIENT_NAME = "client_name"

        const val KEY_SERVER_MESSAGE_ID = "messageId"
        const val KEY_SERVER_ERROR_CODE = "errorCode"
        const val KEY_SERVER_ERROR_MESSAGE = "errorMessage"
        const val KEY_SERVER_RESERVATION = "reservations"
        const val KEY_SERVER_TERMINAL_ID = "terminalId"
        const val KEY_SERVER_PROPERTY_CODE = "propertyCode"
        const val KEY_SERVER_PMS_RES_ID = "pmsResId"
        const val KEY_SERVER_AMOUNT = "amount"
        const val KEY_SERVER_PAYMENT_TYPE = "paymentType"
        const val KEY_SERVER_AUTH_NO = "authNo"
        const val KEY_SERVER_CARD_TYPE = "cardType"
        const val KEY_SERVER_CURRENCY = "currency"
        const val KEY_SERVER_PAYMENT_OPTION = "paymentOption"
        const val KEY_SERVER_PAYMENT_TERMINAL_NO = "paymentTerminalNo"
        const val KEY_SERVER_AUTH_NO_EXPIRY = "authNoExpiry"
        const val KEY_SERVER_AMOUNT_DUE = "amountDue"
        const val KEY_SERVER_ACCESS_TOKEN = "access_token"
        const val KEY_SERVER_TOKEN_TYPE = "token_type"
        const val KEY_SERVER_EXPIRES_IN = "expires_in"
        const val KEY_SERVER_ERROR_2 = "Error"
        const val KEY_SERVER_ERROR_3 = "error"

        const val KEY_LOGIN_CONFIGURE_ALL_PASSWORD = "password"
        const val KEY_LOGIN_CONFIGURE_ALL_PASSWORD_CONFIRM = "password_confirm"
        const val KEY_LOGIN_CONFIGURE_ALL_REQUIRE_PASSWORD_REFUND = "require_password_refund"
        const val KEY_LOGIN_CONFIGURE_ALL_CLIENT_ID = "client_id"
        const val KEY_LOGIN_CONFIGURE_ALL_CLIENT_SECRET = "client_secret"
        const val KEY_LOGIN_CONFIGURE_ALL_CLIENT_NAME = "client_name"
        const val KEY_LOGIN_CONFIGURE_ALL_PROPERTY_CODE = "property_code"
        const val KEY_LOGIN_CONFIGURE_ALL_TERMINAL_ID = "terminal_id"
        const val KEY_LOGIN_CONFIGURE_ALL_PAYMENT_TERMINAL_NUMBER = "payment_terminal_number"
        const val KEY_LOGIN_CONFIGURE_ALL_BASE_URL = "base_url"
        const val KEY_LOGIN_CONFIGURE_ALL_DEFAULT_CURRENCY = "default_currency"

        const val KEY_PASSWORD_LOGIN = "KEY_PASSWORD_LOGIN"
        const val KEY_PASSWORD_REQUIRED_FOR_REFUND = "KEY_PASSWORD_REQUIRED_FOR_REFUND"
        const val PASSWORD_LOGIN_MIN_LENGTH = 6
        const val DEFAULT_REQUIRE_PASSWORD_FOR_REFUND = true
        const val ERROR_CODE_OK = 0
        const val MAX_SIZE_SERVER_INTERACTIONS: UShort = 10u

        val URL_SEPARATOR: String = "/"


        const val keyItemsList = "KEY_ITEMS_LIST"


        val jsonCustom = Json {
            isLenient = true
            ignoreUnknownKeys = true
            allowSpecialFloatingPointValues = true
            useArrayPolymorphism = true
        }

        /**
         * Constant.jsonCustom.decodeFromString(STRING JSON)
        Constant.jsonCustom.encodeToString(OBJETO)*/
    }

    object Network {
        const val URL_BASE = "https://api.capitaland.com/"
        const val URL_BASE_UAT = "https://api-uat.capitaland.com/"

        const val URI_OAUTH = "common/odx/authenticate/v1/token"
        const val URI_RESERVATION_DETAILS = "lodging/channel/payment-terminal/v1/reservationdetailsby-resid"
        const val URI_PAYMENT_RESERVATION = "lodging/channel/payment-terminal/v1/reservation-payment"

        fun getURLBase(buildType: BuildType) = when (buildType) {
            BuildType.RELEASE -> URL_BASE
            else -> URL_BASE_UAT
        }
        fun getURLOAuth(baseURL: String) = "${fixBaseURL(baseURL)}${fixURLUri(URI_OAUTH)}"
        fun getURLReservationDetails(baseURL: String) = "${fixBaseURL(baseURL)}${fixURLUri(URI_RESERVATION_DETAILS)}"
        fun getURLPaymentReservation(baseURL: String) = "${fixBaseURL(baseURL)}${fixURLUri(URI_PAYMENT_RESERVATION)}"

        fun baseHeaders(): Map<String, String> {
            val headers: MutableMap<String, String> = mutableMapOf()
            headers["Content-Type"] = "application/json"
            return headers
        }

        fun fixBaseURL(url: String): String = url.let {
            if(it.endsWith(URL_SEPARATOR))it else "$it$URL_SEPARATOR"
        }
        fun fixURLUri(url: String): String = url.let {
            if (it.startsWith(URL_SEPARATOR)) {
                if (it.length > 1) it.substring(1) else EMPTY_STRING
            } else it
        }
    }

}


fun buildType(type: String): Constant.Companion.BuildType = when (type) {
    Constant.DEBUG -> Constant.Companion.BuildType.DEBUG
    else -> Constant.Companion.BuildType.RELEASE
}




