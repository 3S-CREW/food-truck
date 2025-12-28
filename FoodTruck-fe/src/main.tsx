import React from 'react';
import ReactDOM from 'react-dom/client';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { Provider as JotaiProvider } from 'jotai';
import App from '@/App';
import { Button } from '@toss/tds-mobile';
import '@/index.css';
import { TDSMobileAITProvider } from '@toss/tds-mobile-ait';

// QueryClient 인스턴스 생성
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      // 실패 시 1번만 재시도
      retry: 1,
      // 웹뷰에서는 포커스 재진입 시 리패치 방지
      refetchOnWindowFocus: false,
    },
  },
});

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    {/* 데이터 페칭 */}
    <QueryClientProvider client={queryClient}>
      {/* 전역 상태 관리 */}
      <JotaiProvider>
        {/* 디자인 시스템 / AIT Provider */}
        <TDSMobileAITProvider>
          <Button>테스트</Button>
        </TDSMobileAITProvider>
        {/* <App /> */}
      </JotaiProvider>
    </QueryClientProvider>
  </React.StrictMode>,
);
