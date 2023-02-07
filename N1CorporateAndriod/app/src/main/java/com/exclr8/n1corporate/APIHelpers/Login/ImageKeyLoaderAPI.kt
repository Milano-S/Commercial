package com.exclr8.n1corporate.APIHelpers.Login

import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Login.ImageKeyListResponse
import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.Helpers.ImageLoader
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ImageKeyLoaderAPI {
	lateinit var json: JSONObject
	private val client = OkHttpClient()

	fun get(callback: (Success: Boolean, Offline: Boolean, Exception: String?, ImageKeyList: MutableList<String>?) -> Unit){

//		val request = Request.Builder()
//			.url(URLs().loginURL)
//			.header(PreferenceHelper().getUSERKEYNAME()!!, PreferenceHelper().getUSERKEY()!!)
//			.get()
//			.build()
//
//		try {
//			client.newCall(request).enqueue(object: Callback {
//				override fun onFailure(call: Call, e: IOException) {
//					callback(false, false, GlobalValue.genericError.message, null)
//				}
//
//				override fun onResponse(call: Call, response: Response) {
//					if (response.isSuccessful) {
//						json = JSONObject(response.body()!!.string())
//						val resp = Gson().fromJson(json.toString(), ImageKeyListResponse::class.java)
//						if (!resp.Offline) {
							var ImageKeys = mutableListOf<String>()
							ImageKeys.add("46728ebb-ff2d-40b5-829e-82a024b69f61")
							ImageKeys.add("46877701-2364-4816-a017-bee406a9aa9d")
							ImageKeys.add("87f39610-b779-4874-923b-6333ab3b616c")
							ImageKeys.add("c48134da-f906-4e35-a1a0-3e09b37987fa")
							ImageKeys.add("f4c1a9e7-e661-497d-8f78-a5dc03a393db")
							ImageKeys.add("82ab3baa-6fdd-46e9-bc1a-7939c3667995")
							ImageKeys.add("5a33c15f-6b14-4851-834b-1b89bf1489b6")
							ImageKeys.add("7c86b84d-51d6-4a2c-bb61-33fe0395e442")
							ImageKeys.add("ff90c422-aa89-4f37-96ac-f1ef1cfe0c71")
							ImageKeys.add("9a9c55ee-5f3f-4aea-824f-629b839ed913")
							ImageKeys.add("ba0898c4-5c24-4186-ae8b-da0b92f10bf3")
							ImageKeys.add("d4b6490b-9632-481d-a533-46bb82192fcd")
							ImageKeys.add("7a135cbb-922e-4a77-8616-806f87520c16")
							ImageKeys.add("bfdb4428-5888-4af4-958b-23786e9fb4af")
							ImageKeys.add("15026a97-d42f-4c6b-94bf-641ea9e70820")
							ImageKeys.add("25257f02-62fd-42ef-a663-ff43bccad63f")
							ImageKeys.add("77e56b3a-12b1-47bf-87af-6fd808eed1b2")
							ImageKeys.add("add00256-3218-4ec6-afcd-af0c9c24545a")
							ImageKeys.add("5e3f4da8-1a85-4689-9d94-96fca5a28382")
							ImageKeys.add("d9380ef6-ac0a-4969-a768-982c84231166")
							ImageKeys.add("b89cb7aa-16d9-406a-8386-efe2e33c5768")
							ImageKeys.add("5b41d774-b2ab-46f5-880a-cb6ccc0c6dc6")
							ImageKeys.add("9d7c67de-9bcc-4a65-a477-636b55970b1c")
							ImageKeys.add("2517e304-723f-4a3a-919a-d0c34d5c0256")
							ImageKeys.add("1840bec8-7fcc-4f90-a7c9-42ef68ecc894")
							ImageKeys.add("ba3f8348-3051-4da1-9ba5-4edbbf2cff37")
							ImageKeys.add("9fe8b22b-293d-4306-bd85-12c923407069")
							ImageKeys.add("c48134da-f906-4e35-a1a0-3e09b37987fa")
							ImageKeys.add("288fd1e4-dfe4-4158-a654-2132dd5b1c15")
							ImageKeys.add("287b9b0b-2c04-4fd1-b6bf-256af6a0b8ae")
							ImageKeys.add("87f39610-b779-4874-923b-6333ab3b616c")
							ImageKeys.add("12adb0e6-cca3-4d37-9d66-b1ea0de04217")
							ImageKeys.add("84f6af25-b5e5-43d5-99ae-ceff8c0406d8")
							ImageKeys.add("fe13c600-cb9d-49b5-8a32-064180d0cafd")
							ImageKeys.add("7d2eabac-49d8-41e0-bfc7-cfb9fb61db4e")
							ImageKeys.add("a5e0f78c-2ce5-480e-b7d4-a4bab41411b2")
							ImageKeys.add("c5b97dd8-1dee-42f2-a8a8-994f1abe049e")
							ImageKeys.add("3488f545-24ad-41ff-9b00-57886f1c8093")
							ImageKeys.add("dcf100ac-ae45-407f-b69d-c964220a204d")
							ImageKeys.add("6b9892ab-ef5a-4d28-b9fe-9e41e7b54ff0")
							ImageKeys.add("a3d3c9ad-f07c-44b8-843c-ca9f7ded6334")
							ImageKeys.add("82ab3baa-6fdd-46e9-bc1a-7939c3667995")
							ImageKeys.add("5e2cae71-4642-4516-bc2f-9fd48b319bea")
							ImageKeys.add("419e6af6-9b8d-441b-9272-078ff11ce198")
							ImageKeys.add("c64acd26-fd74-440f-95ee-06eb45a8f8e3")
							ImageKeys.add("cacb75ad-3085-44ec-a116-929d4a10d3e5")
							ImageKeys.add("8ec42fcc-8abb-4be1-b401-ee1c06e50eca")
							ImageKeys.add("c7caa44d-ee40-40f2-b5e9-cdf7a85c5825")
							ImageKeys.add("26cd245b-6459-4d9b-9b74-6e22658d7f17")
							ImageKeys.add("2ef9f2fb-be40-46b1-9d63-bd29634e27b4")
							ImageKeys.add("1c31e742-d55a-46bb-965a-e93c7f3dca44")
							ImageKeys.add("bd73ba75-7fd9-4aa4-961a-b7ac66ef05ba")
							ImageKeys.add("61632c56-8d43-409c-8bed-821cc4268d8e")
							ImageKeys.add("ee46d3a7-a080-48bb-a65f-645fcbec36ba")
							ImageKeys.add("f4c1a9e7-e661-497d-8f78-a5dc03a393db")
							ImageKeys.add("4febf5d3-5c18-419d-be5d-e6c0e539d8eb")
							ImageKeys.add("b479a80b-758e-4331-bba0-634718e412a1")
							ImageKeys.add("d78aac9d-7523-4f19-99a6-aa1d9c2ab913")
							ImageKeys.add("1fd97274-1f29-49a1-9a60-d8a4728473d5")
							ImageKeys.add("abecac09-5127-4101-b5f3-6efae4120e5e")
							ImageKeys.add("58d839f0-f70c-4fdc-b61e-af10093d12b5")
							ImageKeys.add("d04f0af2-0fe1-44ea-b87d-e2b20f4913e6")
							ImageKeys.add("eae79fde-1f0e-417d-8a22-63cfef8fb306")
							ImageKeys.add("c348dc84-4375-40b4-9f4f-1635b684e6fc")
							ImageKeys.add("a3e1fc9a-ee09-4702-be4e-a3c7c50eb2d8")
							ImageKeys.add("6994280c-77d5-49cb-b116-865be1ba6ac1")
							ImageKeys.add("a0f54e1b-7d7d-4ca5-bdf4-9665ac76f1be")
							ImageKeys.add("ef4c157d-18cd-442e-b0b1-3f67488db26d")
							ImageKeys.add("a2237fad-1b7c-45bc-828a-e294ce907a55")
							ImageKeys.add("19d17515-7723-4867-b1bd-f491fc61e89b")
							ImageKeys.add("4e3e6b30-57f1-48bb-8b37-fbb8497d506a")
							ImageKeys.add("add65f23-b294-4539-9ea3-aaed9fb65301")
							ImageKeys.add("8ae769fc-a257-4d50-81a3-7810d92c0f1f")
							ImageKeys.add("faeeb5e4-aa24-4c59-9f3a-e47195d49d04")
							ImageKeys.add("16b2d09e-4e35-4d8a-8783-757df931b43c")
							ImageKeys.add("6f7591fd-b031-48e4-82c9-74f7538980e5")
							ImageKeys.add("35fe8115-d56e-4c8b-b31d-9613b38d38f0")
							ImageKeys.add("2b571440-2dec-4f41-9e56-4bc28e3eb801")
							ImageKeys.add("c1a419ec-a29f-4366-a643-de3c0f2e81e2")
							ImageKeys.add("b5776622-d229-4059-8fc4-9654b79e8dfc")
							ImageKeys.add("a79ff59e-89fb-4ab8-9f1b-d8ff0c9c59c9")
							ImageKeys.add("340c72f5-a740-4953-893c-9b67123ae48d")
							ImageKeys.add("f41bae1b-ec4e-4740-b092-991a5018bd4f")
							ImageKeys.add("da3c4d22-8a44-48db-bd59-c2513487ea56")
							ImageKeys.add("dfde49fd-6eb6-436d-b790-e4fb0ec9b1df")
							ImageKeys.add("748c69ef-8ee3-48a5-82dd-d02ff624f581")
							ImageKeys.add("8d3a08d0-5e37-4094-80b8-7ce69f033015")
							ImageKeys.add("6cc49365-1ecc-40c3-8323-439a03a6f016")
							ImageKeys.add("a1d3abc1-b7ed-43c1-abd0-1798edbb3d8c")
							ImageKeys.add("8936d5b4-683f-41d6-80db-775577dd8066")
							ImageKeys.add("790c3601-3c60-4015-a1c2-485bbedb016b")
							ImageKeys.add("46728ebb-ff2d-40b5-829e-82a024b69f61")
							ImageKeys.add("cf64f48a-e2cc-4b60-86f7-8c6fe6c138ad")
							ImageKeys.add("6971546b-da80-4143-bde1-16a510865cb8")
							ImageKeys.add("fee20d60-68c5-42e8-923a-c0a32fd71932")
							ImageKeys.add("7f37f62d-b9e6-4d6d-afde-f3507d3d84aa")
							ImageKeys.add("a00c39b8-818c-493a-90b2-746308d971f5")
							ImageKeys.add("06ffa7b9-b51b-43b5-ac73-7ba42571b7b7")
							ImageKeys.add("90a03d9a-ac18-433f-b440-d4b250caba5b")
							ImageKeys.add("7d91d31d-0305-4535-a8a3-dfb54fb678f1")
							ImageKeys.add("65aa18cd-23f9-429a-85e4-9469181a7b4f")
							ImageKeys.add("931c947d-3981-4289-a9f6-262fb8ce7801")
							ImageKeys.add("59018da3-bbd6-477e-83db-df6bbb9e9c25")
							ImageKeys.add("57bf7fd2-5f2f-482e-b870-b9d01e967c4c")
							ImageKeys.add("94bac0e9-590c-49e2-8f89-01e49dbf7d68")
							ImageKeys.add("06679ed0-c10c-440c-8c13-d5f344cb7bb4")
							ImageKeys.add("832838e6-2528-46c7-a5cf-8cd86bcb070e")
							ImageKeys.add("fe0a8e66-18c1-4cb8-9986-cb9582e04ad2")
							ImageKeys.add("74c76da6-c2a4-4a40-b4c9-83b1178275ac")
							ImageKeys.add("a39ddaf5-c4f8-42fd-8df8-5a307d64921c")
							ImageKeys.add("a7f006e0-cd85-4e00-acbd-eb602fe6823b")
							ImageKeys.add("855f0a4f-e196-4373-b821-ebafadc16851")
							ImageKeys.add("9e0b5a48-64cc-413c-bda6-d204b4450ce9")
							ImageKeys.add("be6176af-4de9-44c7-a1e6-733b66914476")
							ImageKeys.add("78490e60-fa8e-4fc7-852d-887849cc5221")
							ImageKeys.add("88d390a2-7981-4351-aabe-afdb974e01f6")
							ImageKeys.add("00bc07a2-9b68-47b1-9286-bbbbc5c98b4e")
							ImageKeys.add("d4b6490b-9632-481d-a533-46bb82192fcd")
							ImageKeys.add("463f3fe3-263b-41e3-b215-4d75d5ae1b3a")
							ImageKeys.add("8ced9ff5-54e2-4e3a-9c6f-cf0c59ff3690")
							ImageKeys.add("22f4adba-ea89-4302-9188-fb8f79a11311")
							ImageKeys.add("ab79f681-31f3-45b9-b621-ac69bbd609c5")
							ImageKeys.add("4292a5cd-4ee4-4f69-8e24-c97da82a8669")
							ImageKeys.add("eae5047a-1c41-41a9-9d95-f847b42dcdaf")
							ImageKeys.add("8032b737-c7b1-4d01-8ef9-84d0f4382367")
							ImageKeys.add("eb52c21e-615e-4bb3-837f-248f311603d3")
							ImageKeys.add("4f7be55f-e9c1-404c-8f78-59d3034934f3")
							ImageKeys.add("c447fde3-c76f-45f3-bb49-5f40601128de")
							ImageKeys.add("b3812fcd-1f42-4b31-91ed-90becd2200ac")
							ImageKeys.add("93cb4f3a-b152-47e5-8b15-8678f0aee962")
							ImageKeys.add("8d11e682-47d0-4245-956f-614ca559651a")
							ImageKeys.add("7798d074-8b35-4cb6-8393-0e799f3d35d8")
							ImageKeys.add("3422aee8-9617-4a87-8002-a097744666a5")
							ImageKeys.add("e03c9caf-5696-4498-aace-f0649e979b9f")
							ImageKeys.add("7897c91b-a1d4-4961-8b35-912bf6862754")
							ImageKeys.add("f3cfeba0-eb3a-43d9-9633-73c789da586c")
							ImageKeys.add("cedf0639-014a-4ff6-b976-71156b53a52b")
							ImageKeys.add("a502902d-046d-4dd0-905d-eed8c92ac55f")
							ImageKeys.add("a00e37e4-6540-447d-b20c-e2f04d2d2628")
							ImageKeys.add("88912aa2-fd3e-41c0-8a2f-0a351a658083")
							ImageKeys.add("4bb021c2-399c-4560-9f60-33f628ec3ed9")
							ImageKeys.add("231efc02-7aae-4b5d-b262-d8143bd318b9")
							ImageKeys.add("d000d448-550a-4b75-b2df-dca934b6b6c1")
							ImageKeys.add("40d68c8a-46cf-4204-8215-0034d8f09b6a")
							ImageKeys.add("90496541-302a-4fbc-8985-ddfee65b358a")
							ImageKeys.add("f3de8b21-1cfd-43f3-8b93-74504896bc87")
							ImageKeys.add("a664b2fd-b067-438b-9afa-d46d470b630c")
							ImageKeys.add("5e3469eb-d9ba-4e44-9dc7-e5b939581875")
							ImageKeys.add("c6da38dd-f6d2-43da-ba1f-eac424b5a7d4")
							ImageKeys.add("26506ec7-2432-4a12-b8e0-a0f876812489")
							ImageKeys.add("c9e08e00-81e4-4c8e-b0d8-8df6889d4f45")
							ImageKeys.add("68a4d6b0-5c6c-4cd3-970c-4df63e7290d6")
							ImageKeys.add("14121306-d62f-42a8-b8f7-d52b327b210e")
							ImageKeys.add("4687d18e-81d4-4135-8fe9-73c55a9d3c4d")
							ImageKeys.add("f57a614a-72d8-41db-8aee-21f874ddbadd")
							ImageKeys.add("6bb65739-771a-4303-9f0c-de549addd40c")
							ImageKeys.add("2a653265-dc9d-4ff2-ba5c-40761999f34c")
							ImageKeys.add("53c66305-6f56-4b23-bac4-d167c975740a")
							ImageKeys.add("125822e8-c42f-4e87-9985-9322f15ba65c")
							ImageKeys.add("03b83818-9e52-4834-8fe9-f6d8747377bb")
							ImageKeys.add("2bef0c2d-2a44-4ebd-bed3-05dce18e5834")
							ImageKeys.add("67a3955d-0868-41f0-8fe7-25d604851cb3")
							ImageKeys.add("04813baf-8b4e-4f45-9b46-f9cd580ccc40")
							ImageKeys.add("99e3fc45-8b22-472a-b5c5-47bad5b5294c")
							ImageKeys.add("486499be-f8c2-45ae-8068-f5ae74257b0a")
							ImageKeys.add("a017147e-5665-4c43-8511-fea2f2a98cee")
							ImageKeys.add("918772a3-89c9-4e53-bfec-700298a96c3b")
							ImageKeys.add("9426dde2-c4a7-40fa-8b8d-dfe1de9bb209")
							ImageKeys.add("6d4ef50e-4bcd-472a-8eed-738880883d46")
							ImageKeys.add("77d8d099-be4c-4c4f-8a85-0b0bed15fff2")
							ImageKeys.add("95e72ede-44bf-4dc5-9fd3-fc639731e2d4")
							ImageKeys.add("2e991cf0-3852-4606-ad5f-2e0073e7fa7f")
							ImageKeys.add("b83efef9-5f94-44d0-81a9-5cd83837ed11")
							ImageKeys.add("5085b735-a400-4a0a-aad3-20f0300ec3d7")
							ImageKeys.add("d4c0fb85-2c2e-4f15-8122-8d209396c4e4")
							ImageKeys.add("0e4be636-3174-492c-a098-536af20c1607")
							ImageKeys.add("aa3aa890-d151-4cf1-8440-0d2188c7d546")
							ImageKeys.add("cc16620d-a4ee-4a02-9da8-5c3a27115043")
							ImageKeys.add("ff4f4e4e-9296-46cb-ac7e-ba9b9fa62fbe")
							ImageKeys.add("185011fb-f510-4393-bd9e-7320dd585039")
							ImageKeys.add("71583fe8-3dcf-4f1e-adb2-aae42c81d939")
							ImageKeys.add("269d992e-77c9-4e96-ada2-1d6abc2e94cc")
							ImageKeys.add("c6297c64-d3d0-4dda-b8ec-301571d98fa2")
							ImageKeys.add("f59c9bfa-6ecd-49bf-b17a-8af714a230e1")
							ImageKeys.add("aed5a7e6-0528-4a83-b2fc-b72ba916d639")
							ImageKeys.add("b8cd7ebc-202c-4aa8-82df-c7d955ce74d3")
							ImageKeys.add("39a81a7b-e996-432a-b88c-3103dbf84115")
							ImageKeys.add("b8615566-2d86-4045-b768-c1db07527f30")
							ImageKeys.add("8db023b4-da00-40de-ae67-74b012bace22")
							ImageKeys.add("b0af96f2-129a-4564-8e05-68369cb2a05f")
							ImageKeys.add("5dfc64e9-f592-4bd8-b58c-17957d0563a8")
							ImageKeys.add("3ea1eada-8dc7-492e-ae1f-96bcdd8a013f")
							ImageKeys.add("5b98578b-76c7-411d-8a64-fc37eb47d747")
							ImageKeys.add("2774101e-020d-419d-99a7-3c55e28e33d1")
							ImageKeys.add("3921b45d-718a-4a75-b6cf-c9a7bcb1b33c")
							ImageKeys.add("0f58c431-9d13-4eef-9919-b3fe043c7229")
							ImageKeys.add("2fdbd8ef-86f6-4279-94d0-eeda45dce7da")
							ImageKeys.add("0319363f-8f4c-495f-80fd-2d7dc6bf0b63")
							ImageKeys.add("4bcdcde4-2b8e-4bd5-b382-69c22b35582b")
							ImageKeys.add("af988f0d-32de-4e58-b13b-cd4b083c82a3")
							ImageKeys.add("81e7a3b7-44bb-4af0-a50f-7bb577f19f9b")
							ImageKeys.add("cef3bab1-975a-4ed5-9022-125674f27e7c")
							ImageKeys.add("fd2b60bf-e37c-4a1e-b9b8-13a77b8247d8")
							ImageKeys.add("43fc0d9e-6a85-4d7e-a319-c5263fb58732")
							ImageKeys.add("2fcbfe19-e68f-42a0-b79c-af38c95ff2b3")
							ImageKeys.add("27524066-d8ef-4c17-b0f1-230282fd8e2d")



							callback(true, false, null, ImageKeys)
//						} else {
//							callback(true, true, null, null)
//						}
//					} else {
//						callback(false, false, GlobalValue.genericError.message, null)
//					}
//				}
//			})
//		} catch (e: Exception) {
//			e.printStackTrace()
//			callback(false, false, GlobalValue.genericError.message, null)
//		}
	}
}













































































































































































































