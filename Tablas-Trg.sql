USE [basededatos]
GO
/****** Object:  Table [dbo].[Servicio]    Script Date: 16/10/2025 6:57:45 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Servicio](
	[IdServicio] [int] IDENTITY(1,1) NOT NULL,
	[NombreCliente] [varchar](255) NULL,
	[Telefono] [varchar](255) NULL,
	[TipoServicio] [varchar](255) NULL,
	[FechaServicio] [varchar](255) NULL,
	[Precio] [real] NULL,
	[idCliente] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[IdServicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Servicio] ON 
GO
INSERT [dbo].[Servicio] ([IdServicio], [NombreCliente], [Telefono], [TipoServicio], [FechaServicio], [Precio], [idCliente]) VALUES (1, N'Carlos Gómez', N'3105551234', N'Mantenimiento', N'2025-10-15', 120000, NULL)
GO
INSERT [dbo].[Servicio] ([IdServicio], [NombreCliente], [Telefono], [TipoServicio], [FechaServicio], [Precio], [idCliente]) VALUES (2, N'Felipe Añvarez', N'3103721585', N'Instalacion', N'2025-10-16', 150000, NULL)
GO
INSERT [dbo].[Servicio] ([IdServicio], [NombreCliente], [Telefono], [TipoServicio], [FechaServicio], [Precio], [idCliente]) VALUES (3, N'Santiago', N'3203892150', N'Reparacion', N'2025-10-17', 150000, NULL)
GO
INSERT [dbo].[Servicio] ([IdServicio], [NombreCliente], [Telefono], [TipoServicio], [FechaServicio], [Precio], [idCliente]) VALUES (4, N'SANTIAGO Marin', N'3120586212', N'Instalacion', N'12/10/2025', 800000, N'10394458782')
GO
SET IDENTITY_INSERT [dbo].[Servicio] OFF
GO




CREATE TABLE dbo.HistorialServicio (
    IdHistorial INT IDENTITY(1,1) PRIMARY KEY,
    IdServicio INT FOREIGN KEY REFERENCES Servicio(IdServicio),
    TipoCambio NVARCHAR(50),         -- 'INSERT', 'UPDATE', 'DELETE'
    CampoModificado NVARCHAR(100) NULL,
    ValorAnterior NVARCHAR(255) NULL,
    ValorNuevo NVARCHAR(255) NULL,
    Usuario NVARCHAR(100),
    FechaCambio DATETIME DEFAULT GETDATE()
);




USE [basededatos]
GO

Create TRIGGER [dbo].[trg_Servicio_Delete]
ON [dbo].[Servicio]
AFTER DELETE
AS
BEGIN
    INSERT INTO dbo.HistorialServicio (
        IdServicio,
        TipoCambio,
        CampoModificado,
        ValorAnterior,
        ValorNuevo
        
    )
    SELECT 
        IdServicio,
        'DELETE',
        'Registro Eliminado',
        NombreCliente + ', ' + Telefono + ', ' + TipoServicio + ', ' + FechaServicio + ', ' + CAST(Precio AS VARCHAR),
        NULL
    FROM deleted
END
GO





USE [basededatos]
GO
/****** Object:  Trigger [dbo].[trg_Servicio_Insert]    Script Date: 10/11/2025 5:24:13?a.?m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create TRIGGER [dbo].[trg_Servicio_Insert]
ON [dbo].[Servicio]
AFTER INSERT
AS
BEGIN
    INSERT INTO dbo.HistorialServicio (IdServicio, TipoCambio, CampoModificado, ValorAnterior, ValorNuevo, Usuario)
    SELECT 
        i.IdServicio,
        'INSERT',
        NULL,
        NULL,
        CONCAT('Nuevo registro agregado: ', i.NombreCliente, ', ', i.Telefono, ', ', i.TipoServicio, ', ', i.FechaServicio, ', ', i.Precio),
        SUSER_SNAME()
    FROM inserted i;
END;





USE [basededatos]
GO
/****** Object:  Trigger [dbo].[trg_Servicio_Update]    Script Date: 10/11/2025 5:24:20?a.?m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create   TRIGGER [dbo].[trg_Servicio_Update]
ON [dbo].[Servicio]
AFTER UPDATE
AS
BEGIN
    -- Nombre del Cliente
    INSERT INTO dbo.HistorialServicio (IdServicio, TipoCambio, CampoModificado, ValorAnterior, ValorNuevo, Usuario)
    SELECT i.IdServicio, 'UPDATE', 'NombreCliente', d.NombreCliente, i.NombreCliente, SUSER_SNAME()
    FROM inserted i JOIN deleted d ON i.IdServicio = d.IdServicio
    WHERE ISNULL(i.NombreCliente,'') <> ISNULL(d.NombreCliente,'');

    -- Telefono
    INSERT INTO dbo.HistorialServicio (IdServicio, TipoCambio, CampoModificado, ValorAnterior, ValorNuevo, Usuario)
    SELECT i.IdServicio, 'UPDATE', 'Telefono', d.Telefono, i.Telefono, SUSER_SNAME()
    FROM inserted i JOIN deleted d ON i.IdServicio = d.IdServicio
    WHERE ISNULL(i.Telefono,'') <> ISNULL(d.Telefono,'');

    -- Tipo de Servicio
    INSERT INTO dbo.HistorialServicio (IdServicio, TipoCambio, CampoModificado, ValorAnterior, ValorNuevo, Usuario)
    SELECT i.IdServicio, 'UPDATE', 'TipoServicio', d.TipoServicio, i.TipoServicio, SUSER_SNAME()
    FROM inserted i JOIN deleted d ON i.IdServicio = d.IdServicio
    WHERE ISNULL(i.TipoServicio,'') <> ISNULL(d.TipoServicio,'');

    -- Fecha del Servicio
    INSERT INTO dbo.HistorialServicio (IdServicio, TipoCambio, CampoModificado, ValorAnterior, ValorNuevo, Usuario)
    SELECT i.IdServicio, 'UPDATE', 'FechaServicio', d.FechaServicio, i.FechaServicio, SUSER_SNAME()
    FROM inserted i JOIN deleted d ON i.IdServicio = d.IdServicio
    WHERE ISNULL(i.FechaServicio,'') <> ISNULL(d.FechaServicio,'');

    -- Precio
    INSERT INTO dbo.HistorialServicio (IdServicio, TipoCambio, CampoModificado, ValorAnterior, ValorNuevo, Usuario)
    SELECT i.IdServicio, 'UPDATE', 'Precio', CAST(d.Precio AS NVARCHAR(255)), CAST(i.Precio AS NVARCHAR(255)), SUSER_SNAME()
    FROM inserted i JOIN deleted d ON i.IdServicio = d.IdServicio
    WHERE ISNULL(i.Precio, -1) <> ISNULL(d.Precio, -1);

    -- idCliente
    INSERT INTO dbo.HistorialServicio (IdServicio, TipoCambio, CampoModificado, ValorAnterior, ValorNuevo, Usuario)
    SELECT i.IdServicio, 'UPDATE', 'idCliente', d.idCliente, i.idCliente, SUSER_SNAME()
    FROM inserted i JOIN deleted d ON i.IdServicio = d.IdServicio
    WHERE ISNULL(i.idCliente,'') <> ISNULL(d.idCliente,'');
END;