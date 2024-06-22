create trigger deleteAccountSetEmployeeAccountNull
on Account
after update
as
begin
	if UPDATE(deletedAt)
	begin
		declare @isDeleted datetime2(7)
		declare @accountId int
		select @isDeleted = deletedAt, @accountId = id  from inserted
		if @isDeleted is not null
		begin
			update Employee set accountID = null where accountID = @accountId
		end
	end
end
go


create TRIGGER [trg_INVOICEDETAIL_INSERT] ON [InvoiceDetail]
AFTER INSERT
AS
BEGIN
	DEClare @total FLOAT
	DEClare @invoiceId INT
	SET @invoiceId = (SELECT invoiceId FROM inserted)
	SET @total = (SELECT SUM(price*quantity) FROM InvoiceDetail WHERE invoiceId = @invoiceId)
	UPDATE Invoice SET total = @total WHERE id = @invoiceId
	Declare @invoiceType INT
	SET @invoiceType = (SELECT [dbo].Invoice.[type] FROM Invoice WHERE id = @invoiceId)
	if(@invoiceType = 1)
	begin
		update Session set serviceCost = @total where computerID = (SELECT computerID FROM inserted)
	end
END
GO
-- export -> type = 1
create trigger updateProductStockWhenCreateInvoiceDetail
on InvoiceDetail
after insert
as
BEGIN
	declare @invoiceId int
	declare @invoiceType int
	declare @quantity int
	declare @productId int

	select @invoiceId = invoiceId, @quantity = quantity, @productId = productId
	from inserted
	select @invoiceType = [type]
	from Invoice
	where id = @invoiceId
	declare @stock int
	select @stock = stock
	from Product
	where id = @productId
	if @stock >0
	begin
		IF @invoiceType = 1
	   		UPDATE Product SET stock = stock - @quantity WHERE id = @productId
		ELSE
	   		UPDATE Product SET stock = stock + @quantity WHERE id = @productId
	end
END
go
create TRIGGER  updateProductStockWhenDeleteInvoiceDetail
on InvoiceDetail
after delete
as
BEGIN
	declare @invoiceId int
	declare @invoiceType int
	declare @quantity int
	declare @productId int

	select @invoiceId = invoiceId, @quantity = quantity, @productId = productId
	from deleted
	select @invoiceType = [type]
	from Invoice
	where id = @invoiceId
	declare @stock int
	select @stock = stock
	from Product
	where id = @productId
	if @stock >0
	begin
		IF @invoiceType = 1
	   		UPDATE Product SET stock = stock + @quantity WHERE id = @productId
		ELSE
	   		UPDATE Product SET stock = stock - @quantity WHERE id = @productId
	end
END

GO
create TRIGGER  updateProductStockWhenDeleteInvoice
on Invoice
after update 
as
BEGIN
	if UPDATE(deletedAt)
	BEGIN
		DECLARE @deletedAt datetime2(7)
		DECLARE @invoiceId int
		select @invoiceId = id, @deletedAt = deletedAt from inserted
		if (@deletedAt is not null)
		begin
			delete from InvoiceDetail where invoiceId = @invoiceId
		end
	END
end 