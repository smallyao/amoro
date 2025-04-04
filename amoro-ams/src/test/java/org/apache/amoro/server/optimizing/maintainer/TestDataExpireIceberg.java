/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.amoro.server.optimizing.maintainer;

import static org.apache.amoro.BasicTableTestHelper.SPEC;

import org.apache.amoro.BasicTableTestHelper;
import org.apache.amoro.TableFormat;
import org.apache.amoro.TableTestHelper;
import org.apache.amoro.catalog.BasicCatalogTestHelper;
import org.apache.amoro.catalog.CatalogTestHelper;
import org.apache.amoro.table.PrimaryKeySpec;
import org.apache.iceberg.PartitionSpec;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestDataExpireIceberg extends TestDataExpire {

  @Parameterized.Parameters(name = "{0}, {1}")
  public static Object[] parameters() {
    return new Object[][] {
      // Iceberg format partitioned by timestamp field
      {
        new BasicCatalogTestHelper(TableFormat.ICEBERG),
        new BasicTableTestHelper(false, true, getDefaultProp())
      },
      {
        new BasicCatalogTestHelper(TableFormat.ICEBERG),
        new BasicTableTestHelper(false, false, getDefaultProp())
      },
      // Iceberg format partitioned by timestampz field
      {
        new BasicCatalogTestHelper(TableFormat.ICEBERG),
        new BasicTableTestHelper(
            TABLE_SCHEMA1, PrimaryKeySpec.noPrimaryKey(), SPEC, getDefaultProp())
      },
      {
        new BasicCatalogTestHelper(TableFormat.ICEBERG),
        new BasicTableTestHelper(
            TABLE_SCHEMA1,
            PrimaryKeySpec.noPrimaryKey(),
            PartitionSpec.unpartitioned(),
            getDefaultProp())
      },
      // Iceberg format partitioned by date string field
      {
        new BasicCatalogTestHelper(TableFormat.ICEBERG),
        new BasicTableTestHelper(
            TABLE_SCHEMA2, PrimaryKeySpec.noPrimaryKey(), SPEC2, getDefaultProp())
      },
      {
        new BasicCatalogTestHelper(TableFormat.ICEBERG),
        new BasicTableTestHelper(
            TABLE_SCHEMA2,
            PrimaryKeySpec.noPrimaryKey(),
            PartitionSpec.unpartitioned(),
            getDefaultProp())
      }
    };
  }

  public TestDataExpireIceberg(
      CatalogTestHelper catalogTestHelper, TableTestHelper tableTestHelper) {
    super(catalogTestHelper, tableTestHelper);
  }
}
