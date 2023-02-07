package com.exclr8.n1corporate.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel

@Dao
interface PriceListDao {
    @Query("Delete from PriceListTable")
    fun nuketTable()

    @Insert
    fun insertPriceList(pricelist: ProductSpecificationModel?)

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity from PriceListTable where ProductCategoryId == :prodcatid")
    fun getPriceList(prodcatid: Int): MutableList<ProductSpecificationModel?>

    @Query("select distinct SponsorImageKey from PriceListTable where HasSponsor")
    fun getPriceListSponsors(): MutableList<String>

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity from PriceListTable where SponsorImageKey like :sponsorKey")
    fun getPriceListForSponsorKey(sponsorKey: String): MutableList<ProductSpecificationModel?>

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity from PriceListTable where SalesItemRevisionId = :id")
    fun getPriceListSpec(id: Int): ProductSpecificationModel

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity from PriceListTable where SpecDescription like :search OR SpecConfiguration like :search OR AddedInformation like :search")
    fun search(search: String): MutableList<ProductSpecificationModel?>?

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity  from PriceListTable where OrderFrequencyCount > 0")
    fun getFrequentlyOrderedItems(): MutableList<ProductSpecificationModel?>

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity  from PriceListTable where IsNew")
    fun getNewItems(): MutableList<ProductSpecificationModel?>

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity  from PriceListTable where DiscountedPrice > 0")
    fun getItemsOnPromotion(): MutableList<ProductSpecificationModel?>

    @Query("select rowid, ProductCategoryId, SpecDescription, SpecConfiguration, SalesItemRevisionId, Fresh, Frozen, IsHalaal, IsImported, FullSalesPrice, ImageKey, OrderUnitType, AddedInformation, HasSponsor, SponsorImageKey, OrderFrequencyCount, IsLastOrdered, LastOrderQuantity, LastOrderDate, IsNew, DiscountedPrice, PackSpecification, CutSpecification, CartQuantity  from PriceListTable where IsLastOrdered")
    fun getLastOrderItems(): MutableList<ProductSpecificationModel?>
}